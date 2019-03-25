package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by tianxiong on 2019/3/25.
 */
public class SampleClass {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("this is before.");
                // 注意这里是调用父类, 不要写成invoke了,自己调用自己很快堆栈溢出
                Object result2 = proxy.invokeSuper(obj,args);
                System.out.println("this is after.");
                return result2;
            }
        });

        Person proxyPersion = (Person)enhancer.create();
        // 对代理后的对象补充代理前的值
//        proxyPersion.setAge(20);
        System.out.println(proxyPersion.say("hi"));

        Field[] fields = proxyPersion.getClass().getDeclaredFields();
        Field[] fields1 = proxyPersion.getClass().getSuperclass().getDeclaredFields();
        fields1[0].setAccessible(true);
        fields1[0].set(proxyPersion,"tianxiong");

        System.out.println("--------");
    }
}
