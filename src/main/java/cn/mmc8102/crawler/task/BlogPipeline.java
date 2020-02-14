package cn.mmc8102.crawler.task;


import cn.mmc8102.crawler.domain.Blog;
import cn.mmc8102.crawler.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author mmc
 */
@Component
public class BlogPipeline implements Pipeline {
    @Autowired
    private IBlogService blogService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的招聘详情对象
        Blog blog = resultItems.get("jobInfo");
        if(blog != null){
            //保存到数据库中
            blogService.add(blog);
        }
    }


}
