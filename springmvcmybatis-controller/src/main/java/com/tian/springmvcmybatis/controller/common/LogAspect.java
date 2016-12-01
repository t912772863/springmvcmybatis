package com.tian.springmvcmybatis.controller.common;

import com.tian.springmvcmybatis.dao.common.validation.Length;
import com.tian.springmvcmybatis.dao.common.validation.Validate;
import com.tian.springmvcmybatis.service.common.BusinessException;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 基于AOP切面实现统一的日志管理
 * Created by Administrator on 2016/11/16 0016.
 */
@Aspect
@Component
public class LogAspect {
    private final Logger logger = Logger.getLogger(LogAspect.class);

//    @Before(value = "execution(* com..controller..*.*(..))")
    public void validate(JoinPoint jp) throws Exception{
        //获取参数的值
        Object[] args = jp.getArgs();
        // 获取当前拦截到方法对象
        MethodSignature methodSignature = (MethodSignature)jp.getSignature();
        Method targetMethod = methodSignature.getMethod();
        Map<Annotation[],Object> map = new HashMap<Annotation[],Object>();
        //注解二阶数组
        Annotation[][] annotationss = targetMethod.getParameterAnnotations();
        for (int i = 0; i < annotationss.length; i++) {
            map.put(annotationss[i],args[i]);
        }
        for(Annotation[] a : map.keySet()){
            //查看是否有注解,没有注解,不用验证
            if(a == null || a.length == 0){
                continue;
            }
            // 有注解,按照规则进行一个个解析,看看是否通过
            for (int i = 0; i < a.length; i++) {
                boolean b = Validate.verify(a[i],map.get(a));
                if(!b){
                    logger.error(map.get(a)+"  校验不通过");
                    throw new BusinessException(500,"参数: "+map.get(a)+" 校验不通过!");
                }
                logger.debug(map.get(a)+"  校验通过");
            }
        }

    }


    @Before(value = "execution(* com.tian.springmvcmybatis.controller..*.*(..))")
    public void before(JoinPoint jp) throws Throwable {
        //调用方法前先进行登录验证
        checkLogin();
        // 参数有效性验证
        validate(jp);

        logger.info("====> process in : " + jp.getSignature());
        StringBuffer params = new StringBuffer();
        String oneParam;
        for (Object o : jp.getArgs()) {
            if(o== null){
                continue;
            }
            if(o instanceof Integer || o instanceof Long || o instanceof Double || o instanceof String || o instanceof Boolean ){
                oneParam = o + "";
            }else {
                oneParam = o.toString();
            }
            params.append(oneParam + " ; ");
        }
        logger.info("====> param : " + params.toString());

    }

    private void checkLogin() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getRequestURI();
        // ,如果本次方法就是登录方法则,直接放行
        if("userlogin".equals(uri.replace("/","").replace("\\",""))){
            return;
        }
        if(request.getSession(true).getAttribute("user")==null){
            // 为什么这里的异常会返回给页面,
            throw new BusinessException(501,"请先登录");
        }
    }


    /**
     * 环绕通过会拦截两次,所以换成了前置拦截+后置拦截,来实现日志.
     * 且因为如果同时用了切面和spring的拦截器,会导致,切面中会拦截到多次spring底层的方法,所以,
     * 现在登录验证想办法也通过拦截器来实现,至于出现问题的原因,和解决办法,还有待解决
     * @return
     * @throws Throwable
     */
//    @Around(value = "execution(* com.tian.springmvcmybatis.controller..*.*(..))")
//    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
//        logger.info("====> process in : " + pjp.getSignature());
//        StringBuffer params = new StringBuffer();
//        String oneParam;
//        for (Object o : pjp.getArgs()) {
//            if(o== null){
//                continue;
//            }
//            if(o instanceof Integer || o instanceof Long || o instanceof Double || o instanceof String || o instanceof Boolean ){
//                oneParam = o + "";
//            }else {
//                oneParam = o.toString();
//            }
//            params.append(oneParam + " ; ");
//        }
//        logger.info("====> param : " + params.toString());
//
//        Object result = pjp.proceed();
//        logger.info("====> result : "+result.toString());
//        return result;
//    }

    @AfterReturning(returning="result", value = "execution(* com.tian.springmvcmybatis.controller..*.*(..))")
    public void after(Object result) throws Throwable {
        logger.info("====> result : "+result.toString());
    }

}
