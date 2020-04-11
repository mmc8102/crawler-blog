package cn.mmc8102.blog.web.controller;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.service.IBlogService;
import cn.mmc8102.blog.service.IBlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author mmc
 * 博客相关控制器
 */
@Controller
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IBlogTypeService blogTypeService;
    //@Autowired
    //private IReplyService replyService;

    /**
     * 帖子详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("query_detail/{id}")
    public String getById(@PathVariable("id") Long id, Model model){
        Blog blog = blogService.getById(id);
        blogService.updateReadCount(id);
        model.addAttribute("blog", blogService.getById(id));
        //model.addAttribute("replys",replyService.queryByPid(id));
        model.addAttribute("types", blogTypeService.queryForList());
        model.addAttribute("pageCode", this.genUpAndDownPageCode(blogService.getById(id-1), blogService.getById(id+1)));
        return "blog_detail";
    }

    /**
     * 获取下一篇博客和下一篇博客代码
     *
     * @param prevBlog
     * @param nextBlog
     * @return
     */
    private String genUpAndDownPageCode(Blog prevBlog, Blog nextBlog) {
        StringBuffer pageCode = new StringBuffer();
        if (prevBlog == null || prevBlog.getId() == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='" + "/blog/query_detail/" + prevBlog.getId() + "'>"
                    + prevBlog.getTitle() + "</a></p>");
        }
        if (nextBlog == null || nextBlog.getId() == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='" + "/blog/query_detail/" + nextBlog.getId() + "'>"
                    + nextBlog.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }
}
