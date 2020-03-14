package cn.mmc8102.crawler.service.impl;

import cn.mmc8102.crawler.domain.Blog;
import cn.mmc8102.crawler.mapper.BlogMapper;
import cn.mmc8102.crawler.query.PageResult;
import cn.mmc8102.crawler.query.QueryObject;
import cn.mmc8102.crawler.service.IBlogService;
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
        //判断info是否为空,为空就新增,不为空就更新
        if(info != null){
            return blogMapper.updateById(blog);
        }else{
            return blogMapper.insert(blog);
        }
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
            return new PageResult(list, count, qo.getCurrentPage(),qo.getPageSize());
        }
        return PageResult.empty(qo.getPageSize());
    }
}
