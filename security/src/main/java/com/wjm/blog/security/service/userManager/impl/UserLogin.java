package com.wjm.blog.security.service.userManager.impl;

import com.alibaba.fastjson.annotation.JSONField;
import com.wjm.blog.security.pojo.db1.auto.entity.SysUserDb1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/13-🍀16:54 @version 1.0
 * @description: 用户登录信息对象
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLogin implements UserDetails {

    //  用户对象
    private SysUserDb1 user;

    //  系统权限集合
    private List<String> userRights;

    //  security 使用的权限集合封装对象
    @JSONField(serialize = false)   //  fastjson 不进行序列化
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  把 userRights 封装为 SimpleGrantedAuthority 对象
        if(authorities != null){    //  在登录后,第一次转换,后续不在转换
            return authorities;
        }

        //  将系统权限集合转换为 security 可使用的权限集合
        authorities = userRights.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() == 0 ? true : false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getDelFlag() == 0 ? true : false;
    }
}
