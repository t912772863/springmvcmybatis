package com.tian.springmvcmybatis.controller.common;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * 定义 ThreadLocalRountingDataSource，继承AbstractRoutingDataSource：
 * Created by Administrator on 2016/11/18 0018.
 */
public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.get();
    }
}
