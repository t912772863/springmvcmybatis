package designpattern.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class ProxyUtil {
    /**
     * 获取一个拥有者的代理实例
     * @param person
     * @return
     */
    public static PersonBean getOwnerProxy(PersonBean person){
        /*
         Proxy.newProxyInstance  此代码创建了代理,利用jdk提供的静态方法
          person.getClass().getClassLoader() 获取类加载器,当作参数
          person.getClass().getInterfaces(), 获取要实现的接口
           new OwnerInvocationHandler(person)) 将被代理的对象当作一个参数,传入定义好规则的代理类中
         */
        return (PersonBean) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person));
    }

    /**
     * 获取一个非拥有者的代理实例
     * @param person
     * @return
     */
    public static PersonBean getNotOwnerProxy(PersonBean person){
        return (PersonBean)Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NotOwnerInvocationHadler(person));
    }
}
