package com.wjm.blog.security.config.security;

import com.wjm.blog.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/14-🍀15:05 @version 1.0
 * @description: security 配置文件
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)      //  启用注解权限配置
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    /***
     * @description: 指定密码加密方式
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/14- @version 1.0
     *
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /***
     * @description: 接口权限配置
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/15- @version 1.0
     *
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                // 基于 token，不需要 csrf
                .csrf().disable()
//                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                // 设置 jwtAuthError 处理认证失败、鉴权失败
//                .exceptionHandling().authenticationEntryPoint(jwtAuthError).accessDeniedHandler(jwtAuthError).and()
//                // 下面开始设置权限
                .authorizeRequests()
                .antMatchers("/").permitAll()       //  登录,不登录都能访问
                .antMatchers("/user/login").anonymous()     //  未登录可以访问 , 登录后不允许访问
                .anyRequest().authenticated()   //  其他接口都需要验证后才能访问
//                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前
//                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
//                // 认证用户时用户信息加载配置，注入springAuthUserService
//                .userDetailsService(xxxAuthUserService)
        ;

        //  配置自定义过滤器位置
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //  配置权限异常处理
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)     //  认证失败异常处理器
                .accessDeniedHandler(accessDeniedHandler);      //  权限校验异常处理器

        //  配置跨域
        http.cors();

        return http.build();
    }


    /***
     * @description: 跨域配置
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/15- @version 1.0
     *
     */
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

}
