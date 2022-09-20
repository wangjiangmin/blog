package com.wjm.blog.security.redis;

import com.wjm.blog.security.utils.RedisHandleUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/16-🍀9:55 @version 1.0
 * @description: TODO
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisHandleUtil redis;
    @Test
    public void test(){
        String key = "aaa";
        String value = "bbbb";
        redis.set(key,value);
        System.out.println(redis.get(key));
    }
}
