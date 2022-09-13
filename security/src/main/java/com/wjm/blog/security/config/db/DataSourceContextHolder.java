package com.wjm.blog.security.config.db;

/**
 * @author wjm;1778682202@qq.com    DATETIME: 2022/9/11-🍀17:45 @version 1.0
 * @description: 设置、添加、获取数据源
 */
public class DataSourceContextHolder {


    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dataSource
     */
    public static void setDataSource(DataSources dataSource) {
        CONTEXT_HOLDER.set(dataSource.getValue());
    }

    /**
     * 设置数据源
     *
     * @param dataSource
     */
    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDER.set(dataSource);
    }
    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDataSource() {
        return (String) CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据
     *
     * @return
     */
    public static void clearDataSource() {
        CONTEXT_HOLDER.remove();
    }
}
