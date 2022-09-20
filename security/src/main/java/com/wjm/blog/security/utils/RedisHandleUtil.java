package com.wjm.blog.security.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/15-🍀16:13 @version 1.0
 * @description: redis 操作工具
 */
@Component
public class RedisHandleUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /***
     * @description: 存入数据
     * @param key
     * @param value
     * @return: * @return boolean

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/16-🍀9:07
    **/
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 存入数据,并指定有效时间 , 时间/秒
     * @param key
     * @param value
     * @param time
     * @return: * @return boolean

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/16-🍀9:06
    **/
    public boolean set(String key, String value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * @description: 将 ojbect 转换为 json 字符串后 , 存入 redis
     * @param key
     * @param value
     * @return: * @return boolean

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/15-🍀17:11
    **/
    public boolean set(String key, Object value) {
        String json = JSON.toJSONString(value);
        return set(key,json);
    }
    /**
     * @description: 将 ojbect 转换为 json 字符串后 , 存入 redis ,并指定有效时间 时间/秒
     * @param key
     * @param value
     * @param time
     * @return: * @return boolean

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/16-🍀9:06
    **/
    public boolean set(String key, Object value,long time) {
        String json = JSON.toJSONString(value);
        return set(key,json,time);
    }

    /**
     * @description: 根据 key 获取数据
     * @param key
     * @return: * @return java.lang.Object

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/16-🍀9:59
    **/
//    public Object get(String key) {
//        return key == null ? null : redisTemplate.opsForValue().get(key);
//    }

    public String get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);

    }

    public <T> T get(final String key,Class<T> classType){
        String value = get(key);

        return (T) JSON.parseObject(value, classType);
    }

    /***
     * @description: 指定 key 失效时间
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/15- @version 1.0
     *
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 获取 key 失效时间
     * @param key
     * @return: * @return long

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/16-🍀9:07
    **/
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /***
     * @description: key 是否存在
     * @param key
     * @return: * @return boolean

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/16-🍀9:10
    **/
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 删除 指定 key 的数据
     * @param key
     * @return:

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/16-🍀9:10
    **/
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

}
