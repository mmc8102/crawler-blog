package cn.mmc8102.blog.web.controller.admin;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.query.BlogQueryObject;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.service.IBlogService;
import cn.mmc8102.blog.service.IBlogTypeService;
import cn.mmc8102.blog.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Autowired
    private IBlogTypeService blogTypeService;

    /**
     * 跳转到博客页
     * @return
     */
    @RequestMapping("/index")
    public String blogIndex(){
        return "admin/blog";
    }

    /**
     * 查询博客列表
     * @param qo
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(@ModelAttribute("qo") BlogQueryObject qo, Model model){
        model.addAttribute("types", blogTypeService.queryForList());
        return blogService.query(qo);
    }

    /**
     * 删除博客
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JSONResult delete(Long id){
        JSONResult result = new JSONResult();
        try {
            blogService.delete(id);
            result.setMsg("删除成功!");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/get")
    @ResponseBody
    public Blog getBlogContentById(Long id){
        return blogService.getById(id);
    }
}
