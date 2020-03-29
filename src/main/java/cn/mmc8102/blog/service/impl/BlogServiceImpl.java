package cn.mmc8102.blog.service.impl;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.mapper.BlogMapper;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.query.QueryObject;
import cn.mmc8102.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mmc
 */
@Service
public class BlogServiceImpl implements IBlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int add(Blog blog) {
        //根据url和发布时间查询数据
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("url", blog.getUrl())
                .eq("update_time", blog.getUpdateTime());
        Blog info = blogMapper.selectOne(wrapper);
        if(info == null){
            return blogMapper.insert(blog);
        }
        return 0;
        //判断info是否为空,为空就新增,不为空就更新
        /*if(info != null){
            blog.setUpdateTime(new Date().toString());
            return blogMapper.updateById(blog);
        }else{
            return blogMapper.insert(blog);
        }*/
    }

    @Override
    public Blog getById(Long id) {
        return blogMapper.queryById(id);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = blogMapper.queryForCount(qo);
        if(count > 0){
            List<Blog> list = blogMapper.queryForList(qo);
            return new PageResult(count, list, qo.getPage(), qo.getRows());
        }
        return PageResult.empty(qo.getRows());
    }

    @Override
    public void delete(Long id) {
        Blog blog = blogMapper.queryById(id);
        if(blog != null){
            blog.setStatus(Blog.STATUS_DELETE);
            blogMapper.updateById(blog);
        }else{
            throw new RuntimeException("该博客不存在!");
        }
    }

    @Override
    public void updateReadCount(Long id) {
        blogMapper.updateReadCount(id);
    }

    @Override
    public void update(Blog blog) {
        blogMapper.updateById(blog);
    }
}
