package cn.mmc8102.blog.service;

import cn.mmc8102.blog.domain.Userinfo;
import cn.mmc8102.blog.query.PageResult;

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

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    Userinfo get(Long id);

    /**
     * 查询当前用户信息
     * @return
     */
    PageResult query();

    /**
     * 更新成功
     * @param userinfo
     */
    void update(Userinfo userinfo);

    /**
     * 修改密码
     * @param password
     * @param password1
     */
    void editPWD(String password, String password1) throws Exception;
}
