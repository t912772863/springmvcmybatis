package com.tian.springmvcmybatis.service.common;

import com.tian.common.datasource.MultiTransactional;
import com.tian.springmvcmybatis.dao.common.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.sql.SQLException;

/**
 * Created by tom on 2018/10/19.
 */
@Aspect
@Component
public class MultiTranscationalAspect {
    private static final Logger logger = LoggerFactory.getLogger(MultiTranscationalAspect.class);

    @Before(value = "(execution(* com.tian.springmvcmybatis.service..*Impl.*(..))) && @annotation(multiTransactional)")
    public void before(JoinPoint jp, MultiTransactional multiTransactional){
        // 在service层有该注解的方法被调用后, 开启事务,
        logger.info("process in "+jp.getSignature());
        DataSourceContextHolder.setHadMultiTranscation(true);
    }

    @AfterReturning(value = "(execution(* com.tian.springmvcmybatis.service..*Impl.*(..))) && @annotation(multiTransactional)")
    public void after(JoinPoint jp, MultiTransactional multiTransactional) throws SQLException {
        // 方法正常执行完成,获取到期间所用到的连接, 手动commit, 并设置自动提交为true
        if(DataSourceContextHolder.getHadMultiTranscation()){
            DataSourceContextHolder.commitConnections();
            DataSourceContextHolder.clearHadMultiTranscation();
            DataSourceContextHolder.clearConnections();
        }
    }

    @AfterThrowing(value = "(execution(* com.tian.springmvcmybatis.service..*Impl.*(..))) && @annotation(multiTransactional)")
    public void exception(JoinPoint jp, MultiTransactional multiTransactional) throws SQLException {
        // 执行过程出现异常, 则回滚事务. 后面可以在注解中加入一些方法, 来定义传播特性
        if(DataSourceContextHolder.getHadMultiTranscation()){
            DataSourceContextHolder.rollbackConnections();
            DataSourceContextHolder.clearHadMultiTranscation();
            DataSourceContextHolder.clearConnections();
        }
    }

}
