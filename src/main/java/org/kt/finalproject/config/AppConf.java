package org.kt.finalproject.config;

import org.kt.finalproject.config.interceptor.AuthInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConf implements WebMvcConfigurer {

    @Autowired
    private AuthInter authInter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInter)
                .addPathPatterns("/api/user/**", "/api/simulation/**")
                .excludePathPatterns("/api/user", "/api/user/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH", "OPTIONS");
    }
}
