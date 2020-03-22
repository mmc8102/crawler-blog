package cn.mmc8102.blog.web.controller;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.query.BlogQueryObject;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.service.IBlogService;
import cn.mmc8102.blog.service.IBlogTypeService;
import cn.mmc8102.blog.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangli28
 * @Date: 2020/3/14 20:32
 * 首页相关
 */
@Controller
public class IndexController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IBlogTypeService blogTypeService;

    @RequestMapping("")
    public String index(@ModelAttribute("qo") BlogQueryObject qo, Model model, HttpServletRequest request){
        qo.setStatus(Blog.STATUS_PUBLISH);
        PageResult blogs = blogService.query(qo);
        model.addAttribute("types", blogTypeService.queryForList());
        model.addAttribute("blogs", blogs);
        model.addAttribute("pageCode", PageUtil.genPagination(request.getContextPath()+"/index", blogs.getTotal(), blogs.getCurrentPage(), blogs.getPageSize()));
        return "index";
    }

    @RequestMapping("download")
    public String download(Model model){
        model.addAttribute("types", blogTypeService.queryForList());
        return "download";
    }
}
