package cn.mmc8102.crawler.service.impl;

import cn.mmc8102.crawler.domain.Blog;
import cn.mmc8102.crawler.mapper.BlogMapper;
import cn.mmc8102.crawler.query.PageResult;
import cn.mmc8102.crawler.query.QueryObject;
import cn.mmc8102.crawler.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        Blog info = blogMapper.queryByUrlAndTime(blog.getUrl(), blog.getUpdateTime());
        //判断info是否为空,为空就新增,不为空就更新
        if(info != null){
            return blogMapper.updateByPrimaryKey(blog);
        }else{
            return blogMapper.insert(blog);
        }
    }

    @Override
    public Blog getById(Long id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult query(QueryObject qo) {
        Long count = blogMapper.queryForCount(qo);
        if(count == 0){
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Blog> list = blogMapper.queryForList(qo);
        return new PageResult(count.intValue(), list);
    }
}
