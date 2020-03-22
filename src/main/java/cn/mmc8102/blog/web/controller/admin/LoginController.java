package cn.mmc8102.blog.web.controller.admin;

import cn.mmc8102.blog.domain.Logininfo;
import cn.mmc8102.blog.service.ILogininfoService;
import cn.mmc8102.blog.util.JSONResult;
import cn.mmc8102.blog.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangli28
 * @Date: 2020/3/14 22:58
 * 系统用户登陆相关控制器
 */
@Controller
@RequestMapping("admin")
public class LoginController {
    @Autowired
    private ILogininfoService logininfoService;

    /**
     * 登陆
     * @param username
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public JSONResult login(String username, String password, HttpServletRequest request) {
        JSONResult json = new JSONResult();
        Logininfo current = this.logininfoService.login(username, password);
        if (current != null && current.getUserType() == Logininfo.USER_TYPE_MANAGER) {
            //保存用户登录信息
            UserContext.setCurrent(current);
        }else{
            json.setSuccess(false);
            json.setMsg("用户名或密码错误");
        }
        return json;
    }

    /**
     * 注销
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        UserContext.getSession().removeAttribute(UserContext.USER_IN_SESSION);
        return "redirect:login.ftl";
    }

}
