package com.wjm.blog.security.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import com.wjm.blog.security.service.userManager.impl.UserLogin;
import com.wjm.blog.security.utils.RedisHandleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/19-🍀9:21 @version 1.0
 * @description: token 认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisHandleUtil redis;

    @Value("${login_token_id}")
    private String LOGIN_TOKEN_ID;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //   获取 token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            token = request.getParameter("token");
            if(!StringUtils.hasText(token)){
                //  放行 , 后续过滤器会验证用户信息 , 并阻止访问
                filterChain.doFilter(request,response);
                return;
            }
        }
        //  解析 token
        JWT jwt = null;
        try {
            jwt = JWTUtil.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token 非法!!!");
        }
        jwt.getHeader(JWTHeader.TYPE);
        final Integer userId = (Integer) jwt.getPayload("userId");

        //  从 redis 中获取用户信息
        UserLogin userLogin = redis.get(LOGIN_TOKEN_ID + userId, UserLogin.class);
        if(Objects.isNull(userLogin)){
            throw new RuntimeException("用户未登录!!!");
        }

        //  TODO    获取权限信息 , 并封装到 Authentication 中


        //  UsernamePasswordAuthenticationToken 三参构造是默认验证通过的 , 二参需要自己设置验证通过标记
        // 存入 securityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin, null, userLogin.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //  放行
        filterChain.doFilter(request,response);
    }
}
