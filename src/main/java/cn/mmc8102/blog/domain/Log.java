package cn.mmc8102.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
/**
 * @author 16282
 */
@Setter@Getter
public class Log {

    /**
     * 登陆状态 0.登陆失败 1.登陆成功
     */
    public static final int STATUS_FAILURE = 0;
    public static final int STATUS_SUCCESS = 1;

    private Long id;
    /**
     * 登陆用户名
     */
    private String username;
    /**
     * 登陆时间
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date loginTime;
    private String ip;
    private Integer status;
    private Integer userType;
}