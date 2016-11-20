package com.tian.springmvcmybatis.controller.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 基于AOP切面实现统一的日志管理
 * Created by Administrator on 2016/11/16 0016.
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = Logger.getLogger(LogAspect.class);

    @Around(value = "execution(* com.tian.springmvcmybatis.controller..*.*(..))")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("====> process in : " + pjp.getSignature());
        StringBuffer params = new StringBuffer();
        String oneParam;
        for (Object o : pjp.getArgs()) {
            if(o instanceof Integer || o instanceof Long || o instanceof Double || o instanceof String || o instanceof Boolean ){
                oneParam = o + "";
            }else {
                oneParam = o.toString();
            }
            params.append(oneParam + " ; ");
        }
        logger.info("====> param : " + params.toString());

        Object result = pjp.proceed();
        logger.info("====> result : "+result.toString());
        return result;
    }

}
