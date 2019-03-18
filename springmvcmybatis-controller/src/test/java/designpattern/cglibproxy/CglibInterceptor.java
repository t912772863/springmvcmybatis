package designpattern.cglibproxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by tianxiong on 2019/3/18.
 */
public class CglibInterceptor implements MethodInterceptor {
    private Object target;
    //相当于JDK动态代理中的绑定
    public Object getInstance(Object target) {
        this.target = target;  //给业务对象赋值
        Enhancer enhancer = new Enhancer(); //创建加强器，用来创建动态代理类
        enhancer.setSuperclass(this.target.getClass());  //为加强器指定要代理的业务类（即：为下面生成的代理类指定父类）
        //设置回调：对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实现intercept()方法进行拦
        enhancer.setCallback(this);
        // 创建动态代理类对象并返回
        return enhancer.create();
    }

    /**
     *
     * @param o obj表示增强的对象，即实现这个接口类的一个对象；
     * @param method 代理的方法对象
     * @param objects 方法参数数组
     * @param methodProxy 表示要触发父类的方法对象；
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前...");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println(methodProxy.getSignature());
        System.out.println(method.getDeclaringClass().getSimpleName());
        System.out.println("执行后...");
        return object;
    }
}
