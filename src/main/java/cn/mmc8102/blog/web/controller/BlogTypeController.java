package cn.mmc8102.blog.web.controller;

import cn.mmc8102.blog.domain.BlogType;
import cn.mmc8102.blog.service.IBlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangli28
 * @Date: 2020/3/14 21:00
 * 博客分类相关控制器
 */
@RestController
@RequestMapping("blog_type")
public class BlogTypeController {
    @Autowired
    private IBlogTypeService blogTypeService;

    @RequestMapping("list")
    public List<BlogType> list(){
        return blogTypeService.queryForList();
    }


}
