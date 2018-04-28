package com.tian.springmvcmybatis.dao.common;

/**
 * 定义一个可以设置当前线程的变量的工具类，用于设置对应的数据源名称：
 * Created by Administrator on 2018/4/28 0028.
 */
public class DataSourceContextHolder {
    /**
     * 线程共享变量
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置数据源类型
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取数据源类型
     * @return
     */
    public static String getDataSourceType(){
        return contextHolder.get();
    }

    /**
     * 清除数据源类型.
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }
}
