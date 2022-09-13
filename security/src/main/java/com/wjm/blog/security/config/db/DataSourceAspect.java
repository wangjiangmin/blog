package com.wjm.blog.security.config.db;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/11-🍀17:49 @version 1.0
 * @description: AOP实现动态数据源的切换
 */
@Component
@Order(-100)
@Slf4j
@Aspect
public class DataSourceAspect {

    /***
     * @description: 默认分包 db1 数据源
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/12- @version 1.0
     *
     */
    @Pointcut("execution(* com.wjm.blog.security.pojo.db1..*.*(..))")
    private void db1Aspect() {
    }

    /***
     * @description: 默认分包 db2 数据源
     * @author wjm;1778682202@qq.com    DATETIME: 2022/9/12- @version 1.0
     *
     */
    @Pointcut("execution(* com.wjm.blog.security.pojo.db2..*.*(..))")
    private void db2Aspect() {
    }

    /**
     * 切点: 所有配置 DataSource 注解的方法
     */
    @Pointcut("@annotation(com.wjm.blog.security.config.db.DS)")
    public void dataSourcePointCut() {}


    @Before("db1Aspect()")
    public void db1DataSource() {
        log.info("切换数据源为：{}", DataSources.db1.getValue());
        DataSourceContextHolder.setDataSource(DataSources.db1);
    }

    @Before("db2Aspect()")
    public void db2DataSource() {
        log.info("切换数据源为：{}", DataSources.db2.getValue());
        DataSourceContextHolder.setDataSource(DataSources.db2);
    }

    @Around("dataSourcePointCut()")
    public Object dynamicDataSourcec(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //  获取注解
        DS ds = method.getAnnotation(DS.class);
        // 通过判断 DataSource 中的值来判断当前方法应用哪个数据源
        DataSourceContextHolder.setDataSource(ds.value());

        log.debug("当前数据源: " + ds.value());
        try {
            return point.proceed();
        } finally {
            DataSourceContextHolder.clearDataSource();
            log.debug("清除数据源...");
        }
    }

}
