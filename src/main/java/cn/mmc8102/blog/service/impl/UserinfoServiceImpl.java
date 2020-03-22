package cn.mmc8102.blog.service.impl;

import cn.mmc8102.blog.domain.Userinfo;
import cn.mmc8102.blog.mapper.UserinfoMapper;
import cn.mmc8102.blog.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangli28
 * @Date: 2020/3/16 21:43
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public int add(Userinfo ui) {
        return userinfoMapper.insert(ui);
    }
}
