package com.wjm.blog.security;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/9-🍀14:43 @version 1.0
 * @description: 权限管理后台
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//  SecurityAutoConfiguration.class         //  security    安全框架配置 , 需要不登录访问时系统时排除
//  DataSourceAutoConfiguration.class       //  druid 数据源自动配置 , 动态数据源需要排除
//  MybatisPlusAutoConfiguration.class      //  mybatisPlus 自动配置
@Log4j2
public class App {

    /***
     * @description: 启动权限管理后台
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/9- @version 1.0
     *
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        log.debug(System.getProperty("user.dir"));
    }
}
