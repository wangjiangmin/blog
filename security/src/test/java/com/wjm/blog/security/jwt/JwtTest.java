package com.wjm.blog.security.jwt;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/14-🍀16:23 @version 1.0
 * @description: TODO
 */
public class JwtTest {

    @Test
    public void test(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuaWNrTmFtZSI6ImgybyIsInVzZXJOYW1lIjoid2ptIiwidXNlcklkIjoxfQ.rr5Emh0zjks-j-U5AOuUoitz8zmZraHnAtSLRCykbTc";

        HashMap<String, Object> map = new HashMap<>();
        map.put("username","wjm");
        map.put("password","1234");

        //  token 生成
//        token = JWTUtil.createToken(map, "TOKEN_SALT".getBytes());
        System.out.println(token);

        //  token 验证
        boolean verify = JWTUtil.verify(token, "TOKEN_SALT".getBytes());
        System.out.println(verify);

        //  token 取参
        JWT jwt = JWTUtil.parseToken(token);
        jwt.getHeader(JWTHeader.TYPE);
        Object username = jwt.getPayload("userName");
        System.out.println(username);

    }
}
