package designpattern.cglibproxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by tianxiong on 2019/3/18.
 */
public class TestCglibProxy {
    public static void main(String[] args) {
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
//        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(Persion.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new CglibInterceptor());
        // 创建代理对象
        Persion proxy= (Persion)enhancer.create();
        // 通过代理对象调用目标方法
        System.out.println( proxy.getName());
        System.out.println(proxy.getAge());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 写法2
        Persion persion = new Persion();
        CglibInterceptor cglibInterceptor = new CglibInterceptor();
        Persion proxyPersion = (Persion)cglibInterceptor.getInstance(persion);
        System.out.println(proxyPersion.getAge());
        System.out.println(proxyPersion.getName());


    }
}
