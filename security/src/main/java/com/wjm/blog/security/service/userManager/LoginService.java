package com.wjm.blog.security.service.userManager;

import com.wjm.blog.security.pojo.db1.auto.entity.SysUserDb1;
import com.wjm.blog.security.result.SystemResult;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/15 🍀9:37 @version 1.0
 * @description: 登录服务
 */
public interface LoginService {

    /***
     * @description: 用户登录
     * @param user
     * @return: * @return com.wjm.blog.security.result.SystemResult

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/19-🍀10:42
    **/
    SystemResult login(SysUserDb1 user);

    /***
     * @description: 用户退出
     * @param
     * @return: * @return com.wjm.blog.security.result.SystemResult

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/19-🍀10:43
    **/
    SystemResult logout();
}
