package cn.mmc8102.blog.service;

import cn.mmc8102.blog.domain.Log;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.query.QueryObject;

public interface ILogService {
    /**
     * 添加日志
     * @param log
     */
    void add(Log log);

    /**
     * 日志分页查询
     * @param qo
     * @return
     */
	PageResult query(QueryObject qo);

}
