package com.wjm.blog.security.config.db;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/11-🍀17:37 @version 1.0
 * @description: 多数据配置
 */
@EnableTransactionManagement        //  开启事务管理
@Configuration
@MapperScan("com.wjm.blog.security.pojo")
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 数据源 1
     *
     * @return
     */
    @Bean("db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1")
    public DataSource db1() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源 2
     *
     * @return
     */
    @Bean("db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2")
    public DataSource db2() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * @description: 动态数据源配置
     * @param: db1 数据源 1 ， db2 数据源 2
     * @return:
     * @author czl
     * @date: 2021/6/16 11:02
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2) {
        /** 创建动态数据源决策者 */
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        /** 存放多个数据源 */
        Map<Object, Object> targerDataSource = new HashMap<>(16);
        targerDataSource.put(DataSources.db1.getValue(), db1);
        targerDataSource.put(DataSources.db2.getValue(), db2);
        /** 讲多个数据源注入targetDataSources */
        dynamicDataSource.setTargetDataSources(targerDataSource);
        /** 默认数据源 */
        dynamicDataSource.setDefaultTargetDataSource(db1);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        /** 创建mybatis中的sqlSessionFactoryBean工厂 */
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        /** 向sqlSessionFactoryBean工厂中注入数据源 */
        sqlSessionFactory.setDataSource(this.multipleDataSource(db1(), db2()));
        /** 创建MybatisConfiguration */
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setJdbcTypeForNull(JdbcType.NULL);
        /** 开启驼峰命名规则 */
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        /** 是否开启缓存 */
        mybatisConfiguration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(mybatisConfiguration);
        return sqlSessionFactory.getObject();
    }
}
