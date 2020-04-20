package cn.mmc8102.blog.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户登录信息
 * @author 16282
 *
 */
@Setter
@Getter
public class Logininfo implements Serializable {
	/**
	 * 账号状态 0.正常 1.锁定
	 */
	public static final int STATE_NORMAL = 0;
	public static final int STATE_LOCK = 1;

	/**
	 * 用户类型 0.后台用户 1.前台用户
	 */
	public static final int USER_TYPE_MANAGER = 0;
	public static final int USER_TYPE_CLIENT = 1;

	private Long id;
	private String username;
	private String password;
	/**
	 * 状态
	 */
	private int state;
	/**
	 * 用户类型
	 */
	private int userType;
}
