package cn.mmc8102.blog.mapper;

import cn.mmc8102.blog.domain.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author wangli28
 * @Date: 2020/3/21 20:50
 * 后台系统菜单相关
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 查询系统菜单
     * @return
     */
    List<Menu> queryForRoot();
}
