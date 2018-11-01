package com.tian.springmvcmybatis.dao.common;

import org.springframework.jdbc.datasource.SmartDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 定义一个类, 帮助实现数据源动态切换功能. 继承自一个抽象类AbstractRoutingDataSource(数据源路由规则类)
 * Created by Administrator on 2018/4/28 0028.
 */

public class DynamicDataSource extends AbstractRoutingDataSource implements SmartDataSource {
    /**
     * 勾子方法, 获取到数据源标识, 并返回, 框架根据标识找到目标数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }

    /**
     * 重写这两个获取数据库连接的方法, 在获取到连接后, 把这两个连接处理一下,保存起来
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = determineTargetDataSource().getConnection();
        // 把连接设置到管理器中
        if(DataSourceContextHolder.getHadMultiTranscation()){
            DataSourceContextHolder.setConnection(connection);
        }
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Connection connection = determineTargetDataSource().getConnection(username, password);
        // 把连接设置到管理器中
        if(DataSourceContextHolder.getHadMultiTranscation()){
            DataSourceContextHolder.setConnection(connection);
        }
        return connection;
    }

    /**
     * 实现聪明的数据源(通过实现一个方法, 可以控制spring是否自动关闭数据库连接)
     * @param con
     * @return
     */
    public boolean shouldClose(Connection con) {
        List<Connection> connectionList = DataSourceContextHolder.getConnections();
        if(connectionList == null){
            // 不存在管理的连接,返回true,也就是spring默认的关闭操作
            return true;
        }else {
            // 如果有管理的连接,查看当前要关闭的连接是否是被管理的一个,如果是则先不关闭,后面手动关闭
            if(connectionList.contains(con)){
                return false;
            }
        }
        return true;
    }
}
