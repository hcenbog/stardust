package com.example.stardust.config;

import com.example.stardust.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/6 20:44
 */
@Configuration //加载
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        // 白名单
        List<String> patterns = new ArrayList<String>();
        patterns.add("/common/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/user/UserLogin.html");
        patterns.add("/web/user/UserReg.html");
        patterns.add("/index.html");
        patterns.add("/web/new/news.html");
        patterns.add("/web/bid/pack.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/users/index");
        patterns.add("/districts/**");
        patterns.add("/news/**");
        patterns.add("/products/**");
        // 通过注册工具添加拦截器
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }

}
