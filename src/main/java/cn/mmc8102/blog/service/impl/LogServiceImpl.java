package cn.mmc8102.blog.service.impl;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.domain.Log;
import cn.mmc8102.blog.mapper.LogMapper;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.query.QueryObject;
import cn.mmc8102.blog.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements ILogService {
	@Autowired
	private LogMapper logMapper;


	@Override
	public void add(Log log) {
		logMapper.insert(log);
	}

	@Override
	public PageResult query(QueryObject qo) {
		int count = logMapper.queryForCount(qo);
		if(count > 0){
			List<Blog> list = logMapper.queryForList(qo);
			return new PageResult(count, list, qo.getPage(), qo.getRows());
		}
		return PageResult.empty(qo.getRows());
	}

}
