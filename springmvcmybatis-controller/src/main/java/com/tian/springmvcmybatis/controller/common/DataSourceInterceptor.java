package com.tian.springmvcmybatis.controller.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Aspect
@Component
public class DataSourceInterceptor {

    // 这个服务是连接的另一个数据库,
    @Before(value = "execution(* com.tian.springmvcmybatis.service.RoleServiceImpl.*(..))")
    public void before(JoinPoint jp) {
        DataSourceTypeManager.set(DataSource.SLAVE);
    }
}
