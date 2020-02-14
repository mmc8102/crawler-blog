package cn.mmc8102.crawler.web.controller;

import cn.mmc8102.crawler.domain.Blog;
import cn.mmc8102.crawler.query.BlogQueryObject;
import cn.mmc8102.crawler.query.PageResult;
import cn.mmc8102.crawler.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mmc
 * 博客相关控制器
 */
@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @RequestMapping("query_detail")
    public Blog getById(Long id){
        return blogService.getById(id);
    }

    @RequestMapping("query")
    @ResponseBody
    public PageResult query(BlogQueryObject qo){
        return blogService.query(qo);
    }
}
