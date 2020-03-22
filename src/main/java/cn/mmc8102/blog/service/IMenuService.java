package cn.mmc8102.blog.service;

import cn.mmc8102.blog.domain.Menu;

import java.util.List;

/**
 * @author wangli28
 * @Date: 2020/3/21 20:48
 */
public interface IMenuService {
    /**
     * 查询菜单
     * @return
     */
    List<Menu> queryForMenu();
}
