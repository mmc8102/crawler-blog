package cn.mmc8102.blog.mapper;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.domain.Log;
import cn.mmc8102.blog.query.QueryObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author mmc
 */
public interface LogMapper extends BaseMapper<Log> {


    /**
     * 高级查询和分页
     * @param qo
     * @return
     */
    Integer queryForCount(QueryObject qo);

    List<Blog> queryForList(QueryObject qo);
}