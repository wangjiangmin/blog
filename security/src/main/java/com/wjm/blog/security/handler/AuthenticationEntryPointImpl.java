package com.wjm.blog.security.handler;

import cn.hutool.http.HttpStatus;
import com.wjm.blog.security.result.SystemResult;
import com.wjm.blog.security.utils.HttpUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/21-🍀16:16 @version 1.0
 * @description: 用户认证异常处理器
 */
@Service
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String resultStr = SystemResult.build(HttpStatus.HTTP_UNAUTHORIZED, "用户认证失败!").toString();
        //  处理权限校验异常
        HttpUtil.renderString(response,resultStr);

    }
}
