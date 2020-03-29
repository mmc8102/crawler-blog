package cn.mmc8102.blog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mmc
 * 博客实体类
 */
@Data
public class Blog {
    /**
     * 博客发布状态 1:发布 0:未发布 -1:删除
     */
    public static final int STATUS_NOT_PUBLISH = 0;
    public static final int STATUS_PUBLISH = 1;
    public static final int STATUS_DELETE = -1;

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
     * 摘要
     */
    private String summary;

    /**
     * 文章来源url
     */
    private String url;

    /**
     * 博客类型
     */
    @TableField(exist = false)
    private BlogType blogType;

    /**
     * 博客类型
     */
    private Long typeId;

    /**
     * 阅读量
     */
    private Integer readCount;

    /**
     * 评论量
     */
    private Integer replyCount;

    /**
     * 博客发布时间
     */
    private Date releaseTime;

    /**
     * 关键字
     */
    private String keyWord;
    /**
     * 帖子发布状态
     */
    private Integer status;

    /**
     * 创建时间(收录时间)
     */
    private Date createTime;

    /**
     * 更新时间(原网站发布时间)
     */
    private String updateTime;

    /**
     * 作者
     */
    private String author;

    /**
     * 博客里存在的图片 主要用于列表展示显示缩略图
     */
    @TableField(exist = false)
    private List<String> imagesList=new LinkedList<>();

}