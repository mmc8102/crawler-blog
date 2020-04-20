package cn.mmc8102.blog.web.controller;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.domain.Log;
import cn.mmc8102.blog.domain.Logininfo;
import cn.mmc8102.blog.query.BlogQueryObject;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.service.IBlogService;
import cn.mmc8102.blog.service.IBlogTypeService;
import cn.mmc8102.blog.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
    @Autowired
    private ILogService logService;

    /**
     * 默认跳转到首页
     * @return
     */
    @RequestMapping("")
    public String main(HttpServletRequest request){
        //保存登录日志
        Log log = new Log();
        log.setIp(request.getRemoteHost());
        log.setLoginTime(new Date());
        log.setUserType(Logininfo.USER_TYPE_CLIENT);
        logService.add(log);
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(@ModelAttribute("qo") BlogQueryObject qo, Model model, HttpServletRequest request){
        qo.setStatus(Blog.STATUS_PUBLISH);
        PageResult blogs = blogService.query(qo);
        model.addAttribute("types", blogTypeService.queryForList());
        model.addAttribute("blogs", blogs);
        return "index";
    }

    @RequestMapping("download")
    public String download(Model model){
        model.addAttribute("types", blogTypeService.queryForList());
        return "download";
    }
}
