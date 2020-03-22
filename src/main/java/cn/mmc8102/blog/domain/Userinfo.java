package cn.mmc8102.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户信息
 * @author 16282
 *
 */
@Getter
@Setter
public class Userinfo {
    private Long id;
    /**
     * 真实姓名
     */
	private String realName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别
     */
    private Boolean sex;
    /**
     * 出生日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private Date date;

}