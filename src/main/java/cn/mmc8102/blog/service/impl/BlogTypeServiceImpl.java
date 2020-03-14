package cn.mmc8102.blog.service.impl;

import cn.mmc8102.blog.domain.BlogType;
import cn.mmc8102.blog.mapper.BlogTypeMapper;
import cn.mmc8102.blog.service.IBlogTypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangli28
 * @Date: 2020/3/14 20:58
 */
@Service
public class BlogTypeServiceImpl implements IBlogTypeService {
    @Autowired
    private BlogTypeMapper blogTypeMapper;

    @Override
    public List<BlogType> queryForList() {
        return blogTypeMapper.selectList(new QueryWrapper<>());
    }
}
