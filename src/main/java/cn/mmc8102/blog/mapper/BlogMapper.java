package cn.mmc8102.blog.mapper;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.query.QueryObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author mmc
 * blog相关mapper
 */
public interface BlogMapper extends BaseMapper<Blog> {

    /**
     * 高级查询和分页
     * @param qo
     * @return
     */
    Integer queryForCount(QueryObject qo);

    List<Blog> queryForList(QueryObject qo);

    /**
     * 根据id查询帖子详情,带分类
     * @param id
     * @return
     */
    Blog queryById(Long id);
}