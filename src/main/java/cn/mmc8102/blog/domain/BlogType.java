package cn.mmc8102.blog.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangli28
 * @Date: 2020/3/14 19:18
 */
@Data
@TableName("blogtype")
public class BlogType {
    private Long id;
    /**
     * 博客类型名称
     */
    private String typeName;
    /**
     * 数量
     */
    private Integer blogCount;
}
