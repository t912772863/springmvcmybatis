package com.tian.springmvcmybatis.dao.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 定义一个类, 帮助实现数据源动态切换功能. 继承自一个抽象类AbstractRoutingDataSource(数据源路由规则类)
 * Created by Administrator on 2018/4/28 0028.
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
