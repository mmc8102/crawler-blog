package cn.mmc8102.crawler.service;

import cn.mmc8102.crawler.domain.BlogType;

import java.util.List;

/**
 * @author wangli28
 * @Date: 2020/3/14 20:58
 */
public interface IBlogTypeService {

    List<BlogType> queryForList();
}
