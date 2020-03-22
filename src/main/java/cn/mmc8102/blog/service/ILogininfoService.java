package cn.mmc8102.blog.service;

import cn.mmc8102.blog.domain.Logininfo;

/**
 * @author wangli28
 * @Date: 2020/3/16 21:42
 * 用户账号相关服务
 */
public interface ILogininfoService {
    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    Logininfo login(String username, String password);

    /**
     * 初始化后台用户
     */
    void initAdmin();
}
