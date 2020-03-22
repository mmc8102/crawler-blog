package cn.mmc8102.blog.web.interceptor;

import cn.mmc8102.blog.util.UserContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 专门用于登陆的拦截器
 * @author 16282
 *
 */
@Component
public class AdminLoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//判断登陆逻辑
		if(UserContext.getCurrent() == null) {
			//重定向到登录页
			response.sendRedirect("/admin");
			return false;
		}
		return true;
	}

}
