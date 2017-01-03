package com.tian.springmvcmybatis.dao.common;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.*;
import java.util.Properties;
/**
 * Created by Administrator on 2016/12/30 0030.
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}),
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class PageInterceptor implements Interceptor {
    //插件运行的代码，它将代替原有的方法
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);

            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object parameter=boundSql.getParameterObject();
            if(parameter instanceof PageParam){
                PageParam pageParam = (PageParam)parameter;
                // 分页参数作为参数对象parameterObject的一个属性
                String sql = boundSql.getSql();

                // 重写sql
                String countSql=concatCountSql(sql);
                String pageSql=concatPageSql(sql,pageParam);

                Connection connection = (Connection) invocation.getArgs()[0];

                PreparedStatement countStmt = null;
                ResultSet rs = null;
                int totalCount = 0;
                try {
                    countStmt = connection.prepareStatement(countSql);
                    rs = countStmt.executeQuery();
                    if (rs.next()) {
                        totalCount = rs.getInt(1);
                    }
                } catch (SQLException e) {
                    System.out.println("Ignore this exception"+e);
                } finally {
                    try {
                        rs.close();
                        countStmt.close();
                    } catch (SQLException e) {
                        System.out.println("Ignore this exception"+ e);
                    }
                }

                metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);

                //绑定count
                pageParam.setTotalNumber(totalCount);
            }
        }

        return invocation.proceed();
    }

    /**
     * 拦截类型StatementHandler
     */
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    public void setProperties(Properties properties) {

    }


    public String concatCountSql(String sql){
        StringBuffer sb=new StringBuffer("select count(*) from ");
        sql=sql.toLowerCase();

        if(sql.lastIndexOf("order")>sql.lastIndexOf(")")){
            sb.append(sql.substring(sql.indexOf("from")+4, sql.lastIndexOf("order")));
        }else{
            sb.append(sql.substring(sql.indexOf("from")+4));
        }
        return sb.toString();
    }

    public String concatPageSql(String sql,PageParam pageParam){
        StringBuffer sb=new StringBuffer();
        sb.append(sql);
        int start = (pageParam.getPageNumber()-1)*pageParam.getPageSize();
        sb.append(" limit ").append(start).append(" , ").append(pageParam.getPageSize());
        return sb.toString();
    }

}
