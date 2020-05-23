package cn.mmc8102.blog.service.impl;

import cn.mmc8102.blog.domain.Logininfo;
import cn.mmc8102.blog.domain.Userinfo;
import cn.mmc8102.blog.mapper.LogininfoMapper;
import cn.mmc8102.blog.mapper.UserinfoMapper;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.service.IUserinfoService;
import cn.mmc8102.blog.util.Constant;
import cn.mmc8102.blog.util.MD5;
import cn.mmc8102.blog.util.UserContext;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangli28
 * @Date: 2020/3/16 21:43
 */
@Service
public class UserinfoServiceImpl implements IUserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Autowired
    private LogininfoMapper logininfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int add(Userinfo ui) {
        return userinfoMapper.insert(ui);
    }

    @Override
    public Userinfo get(Long id) {
        return userinfoMapper.selectById(id);
    }

    @Override
    public PageResult query() {
        List<Object> list = new ArrayList<>();
        list.add(userinfoMapper.selectById(UserContext.getCurrent().getId()));
        return new PageResult(1, list, 1 ,1);
    }

    @Override
    public void update(Userinfo userinfo) {
        userinfoMapper.updateById(userinfo);
    }

    @Override
    public void editPWD(String password, String newPwd) throws Exception {
        Logininfo current = UserContext.getCurrent();
        password = MD5.encode(password);
        newPwd = MD5.encode(newPwd);

        if (!password.equals(current.getPassword())) {
            throw new Exception("原密码输入错误!");
        }
        if (password.equals(newPwd)) {
            throw new Exception("新旧密码相同,禁止修改!");
        }
        current.setPassword(newPwd);
        UpdateWrapper<Logininfo> wrapper = new UpdateWrapper<>();
        wrapper.set("password", current.getPassword())
                .eq("id",current.getId());
        logininfoMapper.update(current, wrapper);
        //清除缓存,删除当前用户登录信息
        redisTemplate.delete(Constant.BLOGREADREDISKEY + UserContext.getCurrent().getUsername());
        UserContext.getSession().removeAttribute(UserContext.USER_IN_SESSION);
    }
}
