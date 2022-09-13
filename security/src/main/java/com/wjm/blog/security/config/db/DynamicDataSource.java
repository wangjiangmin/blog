package com.wjm.blog.security.config.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/11-🍀17:44 @version 1.0
 * @description: 动态决策数据源
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * @description:  动态数据源决策
     * @param: null
     * @return:
     * @author
     * @date: 2021/6/16 11:09
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = DataSourceContextHolder.getDataSource();
        log.info("当前数据源为：{}", dataSourceType);
        return dataSourceType;
    }
}
