package com.mydemo.multidatasources.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-30 15:43
 * @Description: 切换数据源用的核心类
 * To change this template use File | Settings | File and Templates.
 */

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSourceType();
    }
}
