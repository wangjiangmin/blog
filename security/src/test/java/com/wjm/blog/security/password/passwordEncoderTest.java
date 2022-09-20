package com.wjm.blog.security.password;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/14-🍀15:20 @version 1.0
 * @description: 用户密码加密测试
 */
public class passwordEncoderTest {



    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("1234");
        System.out.println(encode);
        //  每次都会随机加盐
        String encode2 = bCryptPasswordEncoder.encode("1234");
        System.out.println(encode2);

        //  验证密码
        boolean matches = bCryptPasswordEncoder.matches("1234", encode);
        System.out.println(matches);
        boolean matches1 = bCryptPasswordEncoder.matches("12345", encode);
        System.out.println(matches1);

    }
}
