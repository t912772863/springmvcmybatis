package com.tian.springmvcmybatis.dao.common;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 定义一个可以设置当前线程的变量的工具类，用于设置对应的数据源名称：
 * Created by Administrator on 2018/4/28 0028.
 */
public class DataSourceContextHolder {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);

    /**
     * 线程共享变量, 用于保存当前线程所要用的数据源名字, 框架会根据名字获取数据源
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    /**
     * 当前线程是否要支持多数据源事务
     */
    private static final ThreadLocal<Boolean> hadMultiTranscation = new ThreadLocal<Boolean>();
    /**
     * 某个多数据源事务中所涉及到的连接对象
     */
    private static final ThreadLocal<List<Connection>> connections = new ThreadLocal<List<Connection>>();

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
        String s= contextHolder.get();
        logger.info("get dataSourceThread "+Thread.currentThread().getName());
        return s;
    }

    /**
     * 清除数据源类型.
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }

    /**
     * 设置当前线程是否多数据源事务
     * @param index
     */
    public static void setHadMultiTranscation(boolean index){
        hadMultiTranscation.set(index);
    }

    /**
     * 获取当前线程是否有多数据源事务
     * @return
     */
    public static boolean getHadMultiTranscation(){
        return hadMultiTranscation.get() == null?false:hadMultiTranscation.get();
    }

    /**
     * 清除多数据源标识
     */
    public static void clearHadMultiTranscation(){
        hadMultiTranscation.remove();
    }

    /**
     * 保存连接对象到线程共享变量中
     * @param connection
     */
    public static void setConnection(Connection connection) throws SQLException {
        // 添加前先把, 自动提交设置为false, 也就是开始自己管理事务
        connection.setAutoCommit(false);
        List<Connection> connectionList = connections.get();
        if(connectionList == null){
            connectionList = new ArrayList<Connection>();
            connections.set(connectionList);
        }
        connectionList.add(connection);
    }

    /**
     * 手动提交每一个连接的事务
     * @throws SQLException
     */
    public static void commitConnections() throws SQLException {
        List<Connection> connectionList = connections.get();
        if(connectionList == null){
            return;
        }
        for(Connection c: connectionList){
            c.commit();
            c.setAutoCommit(true);
            c.close();
        }
    }

    /**
     * 获取到所有管理的连接
     * @return
     */
    public static List<Connection> getConnections(){
        return connections.get();
    }

    /**
     * 手动回滚事务
     * @throws SQLException
     */
    public static void rollbackConnections() throws SQLException {
        List<Connection> connectionList = connections.get();
        if(connectionList == null){
            return;
        }
        for(Connection c: connectionList){
            c.rollback();
            c.setAutoCommit(true);
            c.close();
        }
    }

    /**
     * 清除线程变量中的连接
     */
    public static void clearConnections(){
        connections.remove();
    }
}
