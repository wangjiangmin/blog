package com.wjm.blog.security.service.userManager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjm.blog.security.pojo.db1.auto.entity.SysUserDb1;
import com.wjm.blog.security.pojo.db1.auto.service.SysUserAutoDb1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/13-🍀16:46 @version 1.0
 * @description: 实现用户权限验证
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserAutoDb1Service userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //  查询用户
        List<SysUserDb1> list = userService.list(new QueryWrapper<SysUserDb1>().lambda()
                .eq(SysUserDb1::getUserName, username)
                .eq(SysUserDb1::getStatus, 0)
                .eq(SysUserDb1::getDelFlag, 0)
        );
        if(list.isEmpty()){
            throw new RuntimeException("用户名/密码错误!!!");
        }

        //  TODO 查询权限信息
        List<String> userRights = null;
        if("test".equals(list.get(0).getUserName())){
            userRights = new ArrayList<>(Arrays.asList("test"));
        }else if("admin".equals(list.get(0).getUserName())){
            userRights = new ArrayList<>(Arrays.asList("admin"));
        }


        
        return new UserLogin(list.get(0),userRights,null);
    }
}
