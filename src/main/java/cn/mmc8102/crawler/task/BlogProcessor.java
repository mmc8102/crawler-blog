package cn.mmc8102.crawler.task;

import cn.mmc8102.crawler.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;

/**
 * @author mmc
 */
//@Component
public class BlogProcessor implements PageProcessor {
    @Autowired
    private BlogPipeline springDataPipeline;
    @Value("${blog.url}")
    private String url;
    @Value("${blog.proxy.host}")
    private String host;
    @Value("${blog.proxy.port}")
    private int port;

    @Override
    public void process(Page page) {
        //解析页面,获取招聘信息详情的url地址
        List<Selectable> list = page.getHtml().css("div.content div.tab-panel.active a.wrapper").nodes();
        //判断获取到的集合是否为空
        if(list.size() == 0){
            //如果为空,表示这是招聘详情页,解析页面,获取文章信息,保存数据
            this.saveJobInfo(page);
        }else{
            //如果不为空,表示这事列表页,解析出详情页的url地址,放到队列中
            for (Selectable selectable : list) {
                //获取url地址
                String blogUrl = selectable.links().toString();
                if(blogUrl != null){
                    //把获取到的url地址放到任务队列中
                    page.addTargetRequest(blogUrl);
                }
            }

            //获取下一页的url
            String bkUrl = page.getHtml().css("div.pagination li").nodes().get(1).links().toString();
            page.addTargetRequest(bkUrl);
        }

    }

    /**
     * 解析页面,获取招聘详情信息,保存数据
     * @param page
     */
    private void saveJobInfo(Page page) {
        Html html = page.getHtml();
        Blog blog = new Blog();
        blog.setTitle(html.css("div.article .name", "text").toString());
        String content = html.css("div.article textarea", "text").toString();
        blog.setContent(content);
        //blog.setAuthor(" ");
        //blog.setPic();
        blog.setUrl(page.getUrl().toString());
        String readCount = html.css("div.article .updateDate_n_read span", "text").nodes().get(2).toString();
        //blog.setReading(Integer.valueOf(readCount.substring(4, readCount.length() - 1)));
        String updateTime = html.css("div.article .updateDate_n_read span", "text").nodes().get(0).toString().substring(7);
        blog.setUpdateTime(updateTime);
        blog.setCreateTime(new Date());

        //把结果保存起来
        page.putField("jobInfo", blog);
    }

    @Override
    public Site getSite() {
        Site site = Site.me()
                .setCharset("utf-8")
                .setTimeOut(60 * 60 * 1000)
                .setRetrySleepTime(3000)
                .setRetryTimes(3);
        return site;
    }

    /**
     * 启动定时任务
     * initialDelay当任务启动后,等待多久执行方法
     * fixedDelaym每个多久执行方法
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 60*1000)
    public void process(){
        //HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy(host, port)));
        Spider.create(new BlogProcessor())
                .addUrl(url)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000)))
                .thread(5)
                .addPipeline(springDataPipeline)
                //.setDownloader(httpClientDownloader)
                .run();
    }
}
