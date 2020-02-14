package cn.mmc8102.crawler.mapper;

import cn.mmc8102.crawler.domain.Blog;
import cn.mmc8102.crawler.query.QueryObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mmc
 * blog相关mapper
 */
@Mapper
public interface BlogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Blog record);

    Blog selectByPrimaryKey(Long id);

    List<Blog> selectAll();

    int updateByPrimaryKey(Blog record);

    /**
     * 根据url和发布时间查询工作信息
     * @param url
     * @param time
     * @return
     */
    Blog queryByUrlAndTime(String url, String time);


    Long queryForCount(QueryObject qo);

    List<Blog> queryForList(QueryObject qo);
}