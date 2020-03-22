package cn.mmc8102.blog.web.controller.admin;

import cn.mmc8102.blog.query.BlogQueryObject;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangli28
 * @Date: 2020/3/21 21:16
 * 后台博客管理相关控制器
 */
@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {
    @Autowired
    private IBlogService blogService;

    @RequestMapping("/index")
    public String blogIndex(){
        return "admin/blog";
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(@ModelAttribute("qo") BlogQueryObject qo){
        return blogService.query(qo);
    }
}
