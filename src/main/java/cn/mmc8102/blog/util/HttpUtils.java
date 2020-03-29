package cn.mmc8102.blog.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author mmc
 * Http工具类
 */
@Component
public class HttpUtils {
    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm  = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        this.cm.setMaxTotal(100);
        //设置每个主机最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }

    /**
     * 根据请求地址下载页面数据
     * @param url
     * @return 页面数据
     */
    public String doGetHtml(String url){
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:65.0) Gecko/20100101 Firefox/65.0");
        httpGet.setConfig(getConfig());
        CloseableHttpResponse response = null;
        try {
            //发起请求,获取响应
            response = httpClient.execute(httpGet);
            //解析响应
            if(response.getStatusLine().getStatusCode() == 200){
                //判断响应体是否为空,不为空才能使用EntityUtils
                if(response.getEntity() != null){
                    return EntityUtils.toString(response.getEntity(), "utf-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public String doGetImage(String url){
        //获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getConfig());
        CloseableHttpResponse response = null;
        try {
            //发起请求,获取响应
            response = httpClient.execute(httpGet);
            //解析响应
            if(response.getStatusLine().getStatusCode() == 200){
                //判断响应体是否为空,不为空才能使用EntityUtils
                if(response.getEntity() != null){
                    //获取图片后缀
                    String extName = url.substring(url.lastIndexOf("."));
                    String picName = UUID.randomUUID().toString() + extName;
                    //下载图片
                    response.getEntity().writeTo(new FileOutputStream(new File("D:/image/" + picName)));
                    return picName;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
    /**
     * 设置请求信息
     * @return
     */
    private RequestConfig getConfig() {
        //创建连接的最长时间 获取连接的最长时间 数据传输的最长时间
        RequestConfig build = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10000)
                .build();
        return build;
    }


}
