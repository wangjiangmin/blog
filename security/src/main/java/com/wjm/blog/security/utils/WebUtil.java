package com.wjm.blog.security.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/10-🍀14:26 @version 1.0
 * @description: TODO
 */
public class WebUtil {

    /***
     * @description: 向页面发送字符数据
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/10- @version 1.0
     *
     */
    public static String renderString(HttpServletResponse response, String str){
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().printf(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
