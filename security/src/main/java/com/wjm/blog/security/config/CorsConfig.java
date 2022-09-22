package com.wjm.blog.security.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/21-🍀16:43 @version 1.0
 * @description: springboot 配置跨域,使用 security 后,也需要在 security 配置中配置跨域
 */
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("get","post","delete","put")
                .allowedHeaders("*")
                .maxAge(3600)
                ;

//        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
