package com.wjm.blog.security.service.userManager.impl;

import cn.hutool.jwt.JWTUtil;
import com.wjm.blog.security.pojo.db1.auto.entity.SysUserDb1;
import com.wjm.blog.security.result.SystemResult;
import com.wjm.blog.security.service.userManager.LoginService;
import com.wjm.blog.security.utils.RedisHandleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/15-🍀9:36 @version 1.0
 * @description: 登录服务实现
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${token_salt}")
    private String TOKEN_SALT;

    @Value("${login_token_id}")
    private String LOGIN_TOKEN_ID;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RedisHandleUtil redis;



    /***
     * @description: 用户登录认证
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/15- @version 1.0
     *
     */
    @Override
    public SystemResult login(SysUserDb1 user) {
        //  用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //  认证失败 , 抛异常提示
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登录失败!!");
        }

        //  认证通过 , 生成 jwt , 并返回给前端
        UserLogin userLogin = (UserLogin) authenticate.getPrincipal();
        user = userLogin.getUser();
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        map.put("userName",user.getUserName());
        map.put("nickName",user.getNickName());
        map.put("createTime",System.currentTimeMillis());

        String token = JWTUtil.createToken(map, TOKEN_SALT.getBytes());

        //  把完整信息存入 redis
        redis.set(LOGIN_TOKEN_ID + user.getUserId(),userLogin);


        HashMap<Object, Object> res = new HashMap<>();
        res.put("token", token);

        return SystemResult.build(200,"登录成功!!",res);
    }

    /***
     * @description: 用户退出
     * @param
     * @return: * @return com.wjm.blog.security.result.SystemResult

     * @author jiangming.wang@qq.com  DATETIME: 2022/9/19-🍀10:44
    **/
    @Override
    public SystemResult logout() {
        //  获取 securityContextHolder 中的用户id
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserLogin user = (UserLogin) authenticationToken.getPrincipal();
        Long userId = user.getUser().getUserId();

        //  删除 redis 中的数据
        redis.del(LOGIN_TOKEN_ID + userId);

        return SystemResult.build(200,"退出成功!!!");
    }
}
