package cn.mmc8102.blog.crawler;

import cn.mmc8102.blog.domain.Blog;
import cn.mmc8102.blog.util.HtmlRegexpUtil;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;

/**
 * @author mmc
 */
@Component
public class BlogProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        //解析页面,获取招聘信息详情的url地址
        List<Selectable> list = page.getHtml().css("#main #post_list .post_item").nodes();
        //判断获取到的集合是否为空
        if(list.size() == 0){
            //如果为空,表示这是博客详情页,解析页面,获取文章信息,保存数据
            this.saveBlogInfo(page);
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
            String nextUrl = page.getUrl().toString();
            if("https://www.cnblogs.com".equals(nextUrl)){
                //当前是首页,获取第二页url
                nextUrl = page.getHtml().css("#pager_bottom .pager a").nodes().get(1).links().toString();
            }else{
                //获取下一页的url
                Integer pageCode = Integer.valueOf(nextUrl.substring(nextUrl.indexOf('p',10)+2));
                //Integer pageCode = Integer.valueOf(nextUrl.substring(nextUrl.length() - 1, nextUrl.length()));
                nextUrl = nextUrl.replace(pageCode.toString(), String.valueOf(pageCode<=200?pageCode+1:200));
            }
            page.addTargetRequest(nextUrl);
        }

    }

    /**
     * 解析页面,获取博客信息,保存数据
     * @param page
     */
    private void saveBlogInfo(Page page) {
        Html html = page.getHtml();
        Blog blog = new Blog();
        blog.setTitle(html.css("#topics .post .postTitle a", "text").toString());
        String content = "";
        try {
            content = html.css("#topics .post .postBody #cnblogs_post_body").toString().trim();
        }catch (Exception e){
            return;
        }
        String contentSubInnerHtml = content.substring(content.indexOf('<',1));
        //获取文章内容带html
        blog.setContent(contentSubInnerHtml.substring(0, contentSubInnerHtml.length() - 6));
        //从内容中截取摘要,不带html
        blog.setSummary(HtmlRegexpUtil.filterHtml(blog.getContent()).replace("\n", "").trim().substring(0, 150));
        blog.setUrl(page.getUrl().toString());
        //String readCount = html.css("#topics .post .postDesc span#post_view_count", "text").toString();
        blog.setReadCount(0);
        String updateTime = html.css("#topics .post .postDesc #post-date", "text").toString();
        blog.setUpdateTime(updateTime);
        blog.setCreateTime(new Date());
        blog.setReplyCount(0);
        blog.setStatus(Blog.STATUS_NOT_PUBLISH);
        //把结果保存起来
        page.putField("blogInfo", blog);
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


}
