package cn.mmc8102.blog.service.impl;

import cn.mmc8102.blog.domain.Menu;
import cn.mmc8102.blog.mapper.MenuMapper;
import cn.mmc8102.blog.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangli28
 * @Date: 2020/3/21 20:49
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryForMenu() {
        return menuMapper.queryForRoot();
    }
}
