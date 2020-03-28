package cn.mmc8102.blog.service;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.query.QueryObject;

/**
 * @author mmc
 * 博客相关服务
 */
public interface IBlogService {

    /**
     * 保存
     * @param blog
     * @return
     */
    int add(Blog blog);

    /**
     * 根据id获取博客详情
     * @param id
     * @return
     */
    Blog getById(Long id);

    /**
     * 高级查询和分页
     * @param qo
     * @return
     */
    PageResult query(QueryObject qo);

    /**
     * 删除博客
     * @param id
     */
    void delete(Long id);

    /**
     * 根据博客阅读量
     * @param id
     */
    void updateReadCount(Long id);
}
