package com.wjm.blog.security.controller.user;

import com.wjm.blog.security.pojo.db1.auto.entity.SysUserDb1;
import com.wjm.blog.security.result.SystemResult;
import com.wjm.blog.security.service.userManager.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/15-🍀9:30 @version 1.0
 * @description: 用户登录页面
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @RequestMapping("/toLogin")
    public ModelAndView toLogin(ModelAndView mv){
        
        return mv;
    }

    /***
     * @description:  用户登录
     * @param user
     * @return: * @return com.wjm.blog.security.result.SystemResult

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/19-🍀10:43
    **/
    @PostMapping("/login")
    public SystemResult login(@RequestBody SysUserDb1 user){

        return loginService.login(user);
    }

    /***
     * @description: 用户退出
     * @param
     * @return: * @return com.wjm.blog.security.result.SystemResult

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/19-🍀10:43
    **/
    @RequestMapping("/logout")
    public SystemResult logout(){

        return loginService.logout();
    }
}
