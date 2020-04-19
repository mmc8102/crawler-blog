package cn.mmc8102.blog.crawler.task;

import cn.mmc8102.blog.crawler.BlogPipeline;
import cn.mmc8102.blog.crawler.BlogProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * @author wangli
 * @Date: 2020/3/15 1:32
 */
@Component
public class CrawlerTask {
    @Autowired
    private BlogPipeline springDataPipeline;
    @Value("${blog.url}")
    private String url;
    @Value("${blog.proxy.host}")
    private String host;
    @Value("${blog.proxy.port}")
    private int port;

    /**
     * 启动定时任务
     * initialDelay当任务启动后,等待多久执行方法
     * fixedDelaym每个多久执行方法
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 60*60*1000)
    public void process(){
        //HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        //使用代理
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
