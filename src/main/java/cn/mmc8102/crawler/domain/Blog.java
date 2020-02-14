package cn.mmc8102.crawler.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author mmc
 * 博客实体类
 */
@Data
public class Blog {
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 作者
     */
    private String author;

    /**
     * 文章来源url
     */
    private String url;

    /**
     * 图片
     */
    private String pic;

    /**
     * 阅读量
     */
    private Integer reading;

    /**
     * 创建时间(收录时间)
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}