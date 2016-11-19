package com.tian.springmvcmybatis.controller.common;

/**
 * 通过 TheadLocal 来保存每个线程选择哪个数据源的标志(key)：
 * Created by Administrator on 2016/11/18 0018.
 */
public class DataSourceTypeManager {
    private static final ThreadLocal<DataSource> dataSourceTypes = new ThreadLocal<DataSource>(){
        @Override
        protected DataSource initialValue(){
            return DataSource.MASTER;
        }
    };

    public static DataSource get(){
        return dataSourceTypes.get();
    }

    public static void set(DataSource dataSourceType){
        dataSourceTypes.set(dataSourceType);
    }

    public static void reset(){
        dataSourceTypes.set(DataSource.MASTER);
    }
}
