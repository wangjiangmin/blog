package com.wjm.blog.security.service.rigihtManager.impl;

import com.wjm.blog.security.mapper.SysUserRightMapper;
import com.wjm.blog.security.service.rigihtManager.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiangmin.wang.qq.com    DATETIME: 2022/9/21-🍀14:26 @version 1.0
 * @description: 权限服务实现
 */

@Service
public class RightServiceImpl implements RightService {

    @Autowired
    SysUserRightMapper rightMapper;

    @Override
    public List<String> queryUserRight(long userId) {
        return rightMapper.selectRightByUserId(userId);
    }
}
