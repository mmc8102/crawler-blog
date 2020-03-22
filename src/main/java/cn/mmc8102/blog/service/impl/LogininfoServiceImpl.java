package cn.mmc8102.blog.service.impl;

import cn.mmc8102.blog.domain.Logininfo;
import cn.mmc8102.blog.domain.Userinfo;
import cn.mmc8102.blog.mapper.LogininfoMapper;
import cn.mmc8102.blog.service.ILogininfoService;
import cn.mmc8102.blog.service.IUserinfoService;
import cn.mmc8102.blog.util.Constant;
import cn.mmc8102.blog.util.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangli28
 * @Date: 2020/3/16 21:44
 */
@Service
public class LogininfoServiceImpl implements ILogininfoService {
    @Autowired
    private LogininfoMapper logininfoMapper;
    @Autowired
    private IUserinfoService userinfoService;

    @Override
    public Logininfo login(String username, String password) {
        QueryWrapper<Logininfo> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username)
               .eq("password", MD5.encode(password));
        return logininfoMapper.selectOne(wrapper);
    }

    @Override
    public void initAdmin() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_type", Logininfo.USER_TYPE_MANAGER);
        int count = logininfoMapper.selectCount(wrapper);
        if(0 == count){
            Logininfo admin = new Logininfo();
            admin.setUsername(Constant.DEFAULT_ADMIN_USERNAME);
            admin.setPassword(MD5.encode(Constant.DEFAULT_ADMIN_PASSWORD));
            admin.setState(Logininfo.STATE_NORMAL);
            admin.setUserType(Logininfo.USER_TYPE_MANAGER);
            logininfoMapper.insert(admin);
            //初始化用户信息
            Userinfo ui = new Userinfo();
            ui.setId(admin.getId());
            userinfoService.add(ui);
        }
    }
}
