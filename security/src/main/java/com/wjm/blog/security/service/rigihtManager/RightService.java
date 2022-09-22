package com.wjm.blog.security.service.rigihtManager;

import java.util.List;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/21 🍀14:25 @version 1.0
 * @description: 权限服务接口
 */
public interface RightService {

    /**
     * @description: 查询用户权限
     * @param userId
     * @return: * @return java.util.List<java.lang.String>

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/21-🍀14:30
    **/
    List<String> queryUserRight(long userId);
}
