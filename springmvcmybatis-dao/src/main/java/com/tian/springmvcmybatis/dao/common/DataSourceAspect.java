package com.tian.springmvcmybatis.dao.common;

import com.tian.common.datasource.DataSource;
import com.tian.springmvcmybatis.dao.entity.Role;
import com.tian.springmvcmybatis.dao.entity.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import sun.reflect.generics.tree.ClassSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 该切面用来实现读写分离, 切换不同的数据源.
 * 该示例中用的是通过方法名来判断是读还是写, 还可以通过注解的方式来判断.
 * 该原理还适用于处理表的水平分割, 比如匹配到特定注解, 则获取到参数, 通过转换得到水平表名的后缀部分, 然后动态拼接表名
 * Created by Administrator on 2016/11/16 0016.
 */
@Aspect
@Component
public class DataSourceAspect {
    private final Logger logger = Logger.getLogger(DataSourceAspect.class);

    /**
     * 两个数据源, 实现读写分离的策略
     * @param jp
     * @throws Throwable
     */
//    @Before(value = "execution(* com.tian.springmvcmybatis.dao..*Mapper.*(..))")
//    public void before(JoinPoint jp) throws Throwable {
//        logger.info("====> process in "+jp.getSignature());
//
//        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
//        Method method = methodSignature.getMethod();
//        String methodName = method.getName();
//
//        if(methodName.startsWith("query")||methodName.startsWith("select")||methodName.startsWith("get")||methodName.startsWith("fetch")){
//            // 如果方法名以如上开头, 则一般为查询,找从库
//            DataSourceContextHolder.setDataSourceType("dataSourceSlaver");
//            logger.info("use slaver dataSource");
//        }else {
//            // 否则就是增删改了,用主库
//            DataSourceContextHolder.setDataSourceType("dataSourceMaster");
//            logger.info("use master dataSource");
//        }
//
//    }

    /**
     * 测试多数据源, 是否可以同时维护多个事务
     * @param jp
     * @throws Throwable
     */
    @Before(value = "execution(* com.tian.springmvcmybatis.dao..*Mapper.*(..))")
    public void before(JoinPoint jp) throws Throwable {
        logger.info("====> process in "+jp.getSignature());
        MethodSignature methodSignature = (MethodSignature)jp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        DataSource dataSourceAnnotatin = targetMethod.getDeclaringClass().getAnnotation(DataSource.class);
        if(dataSourceAnnotatin == null){
            DataSourceContextHolder.setDataSourceType("dataSourceMaster");
        }else {
            String dataSourceName = dataSourceAnnotatin.value();
            DataSourceContextHolder.setDataSourceType(dataSourceName);
            logger.info("set dataSourceThread "+Thread.currentThread().getName());
        }
    }

    @After(value = "execution(* com.tian.springmvcmybatis.dao..*Mapper.*(..))")
    public void after(JoinPoint jp) throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
    }
}
