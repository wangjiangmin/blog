package com.wjm.blog.security.handler;

import cn.hutool.http.HttpStatus;
import com.wjm.blog.security.result.SystemResult;
import com.wjm.blog.security.utils.HttpUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/21-🍀16:34 @version 1.0
 * @description: 用户权限校验异常处理器
 */
@Service
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String resultStr = SystemResult.build(HttpStatus.HTTP_UNAUTHORIZED, "用户权限校验失败!").toString();
        //  处理权限校验异常
        HttpUtil.renderString(response,resultStr);
    }
}
