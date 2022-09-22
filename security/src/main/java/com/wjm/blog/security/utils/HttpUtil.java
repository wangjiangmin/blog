package com.wjm.blog.security.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.druid.support.spring.stat.annotation.Stat;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/21-🍀15:51 @version 1.0
 * @description: TODO
 */
@Log4j2
public class HttpUtil {

    /***
     * @description: 打印 用户访问信息
     * @param request
     * @return:

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/21-🍀15:52
    **/
    public static void noLoginUserInfo(HttpServletRequest request){
        UserAgent ua = UserAgentUtil.parse(request.getHeader("user-agent"));
        log.info("------ 未登录用户访问的信息 ----------");
        log.info("浏览器: " + ua.getBrowser().toString());
        log.info("浏览器版本: " + ua.getVersion());
        log.info("浏览器引擎: " + ua.getEngine().toString());
        log.info("浏览器引擎版本: " + ua.getEngineVersion());
        log.info("系统: " + ua.getOs().toString());
        log.info("平台: " + ua.getPlatform().toString());
        log.info("ip: " + getIpAddress(request));
        log.info("----------------------------------");
    }

    /***
     * @description: 获取用户 ip
     * @param request
     * @return: * @return java.lang.String
     * @author jiangming.wang@qq.com  DATETIME: 2022/9/21-🍀16:00
    **/
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            return ip.split(",")[0];
        } else {
            return ip;
        }
    }

    /***
     * @description: 返回 200
     * @param response
     * @param str
     * @return: * @return java.lang.String

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/21-🍀16:36
    **/
    public static String renderString(HttpServletResponse response,String str) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
