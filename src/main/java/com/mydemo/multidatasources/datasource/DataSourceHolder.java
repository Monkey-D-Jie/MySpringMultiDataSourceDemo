package com.mydemo.multidatasources.datasource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 15:44
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class DataSourceHolder {
    //线程本地环境
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * @Description: 设置数据源类型
     * @param dataSourceType  数据库类型
     * @return void
     * @throws
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * @Description: 获取数据源类型
     * @param
     * @return String
     * @throws
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * @Description: 清除数据源类型
     * @param
     * @return void
     * @throws
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}

