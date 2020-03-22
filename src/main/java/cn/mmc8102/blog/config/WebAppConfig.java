package cn.mmc8102.blog.config;

import cn.mmc8102.blog.web.interceptor.AdminLoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 系统配置类
 * @author mmc
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    @Autowired
    private AdminLoginCheckInterceptor adminLoginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginCheckInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin");
    }
}
