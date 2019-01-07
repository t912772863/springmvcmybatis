package com.tian.springmvcmybatis.dao.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 由于springboot工程中没有web容器, 所以不能用常规的方法 WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext(); 来获取上下文对像容器.
 * 所以需要像本示例一个实现一个接口, 把上下文对象保存到自定义的类中, 并提供方法方法
 *
 * 然后就可以通过 applicationContext.getBean(XX.class). 拿到容器中的对象了
 *
 * Created by tom on 2018/10/13.
 */
@Component
public class ApplicationHelper implements ApplicationContextAware {
    public ApplicationHelper(){
        System.out.println("init spring application");
    }

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
