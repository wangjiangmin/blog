package com.wjm.blog.security.config.db;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/11-🍀17:43 @version 1.0
 * @description: 枚举 DataSources 类
 */
public enum DataSources {

    db1("db1"),
    db2("db2");

    private String value;

    DataSources(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
