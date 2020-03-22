package cn.mmc8102.blog.web.controller.admin;

import cn.mmc8102.blog.service.IUserinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangli28
 * @Date: 2020/3/16 21:46
 */
@Controller
@RequestMapping("admin/user")
public class UserinfoController {
    @Autowired
    private IUserinfoService userinfoService;



}
