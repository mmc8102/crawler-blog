package cn.mmc8102.blog.web.controller.admin;

import cn.mmc8102.blog.domain.Menu;
import cn.mmc8102.blog.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wangli28
 * @Date: 2020/3/21 20:43
 */
@Controller
@RequestMapping("admin")
public class AdminIndexController {
    @Autowired
    private IMenuService menuService;


    /**
     * 跳转到登录页
     * @return
     */
    @RequestMapping("")
    public String index(){
        return "admin/login";
    }

    /**
     * 跳转到主页
     * @return
     */
    @RequestMapping("index")
    public String main(){
        return "admin/index";
    }

    /**
     * 获取菜单
     * @return
     */
    @RequestMapping("/queryForMenu")
    @ResponseBody
    public List<Menu> queryForMenu(){
        return menuService.queryForMenu();
    }
}
