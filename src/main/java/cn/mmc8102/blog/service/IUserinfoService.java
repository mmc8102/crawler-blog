package cn.mmc8102.blog.service;

import cn.mmc8102.blog.domain.Userinfo;

/**
 * @author wangli28
 * @Date: 2020/3/16 21:44
 * 用户信息相关服务
 */
public interface IUserinfoService {
    /**
     * 添加用户信息
     * @param ui
     * @return
     */
    int add(Userinfo ui);
}
