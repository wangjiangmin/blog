package com.wjm.blog.security.mapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjm.blog.security.config.db.DS;
import com.wjm.blog.security.config.db.DataSources;
import com.wjm.blog.security.mapper.SysUserRightMapper;
import com.wjm.blog.security.pojo.db1.auto.entity.SysUserDb1;
import com.wjm.blog.security.pojo.db1.auto.mapper.SysUserAutoDb1Mapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/13-🍀16:17 @version 1.0
 * @description: TODO
 */
@SpringBootTest
@Log4j2
public class MapperTest {

    @Autowired
    private SysUserAutoDb1Mapper userMapper;

    @Autowired
    private SysUserRightMapper userRightMapper;

    @Test
    public void test() {
        List<SysUserDb1> users = userMapper.selectList(new QueryWrapper<>());
        log.debug(users);
    }

    @Test
    @DS(DataSources.db1)
    public void test2(){
        List<String> strings = userRightMapper.selectRightByUserId(1);
        log.debug(strings);
    }
}
