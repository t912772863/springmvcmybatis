package cglib;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.beans.ImmutableBean;
import net.sf.cglib.proxy.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by tianxiong on 2019/3/25.
 */
public class TestEnhancer {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        testBeanMap();
    }

    /**
     *  下面代码中，FixedValue用来对所有拦截的方法返回相同的值，从输出我们可以看出来，Enhancer对非final方法test()、toString()、hashCode()进行了拦截，
     *  没有对getClass进行拦截。由于hashCode()方法需要返回一个Number，但是我们返回的是一个String，这解释了上面的程序中为什么会抛出异常。
     *
     *  Enhancer.setSuperclass用来设置父类型，从toString方法可以看出，使用CGLIB生成的类为被代理类的一个子类，形如：SampleClass$$EnhancerByCGLIB$$e3ea9b7
     *
     *  Enhancer.create(Object…)方法是用来创建增强对象的，其提供了很多不同参数的方法用来匹配被增强类的不同构造方法。（虽然类的构造放法只是Java字节码层面的函数，
     *  但是Enhancer却不能对其进行操作。Enhancer同样不能操作static或者final类）。我们也可以先使用Enhancer.createClass()来创建字节码(.class)，然后用字节码动态的生成增强后的对象。
     */
    public static void testFixedValue() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return "hello cglib";
            }
        });
        Person proxyPersion = (Person)enhancer.create();
        System.out.println(proxyPersion.say("hi"));
        System.out.println(proxyPersion.toString());
        System.out.println(proxyPersion.getClass());
        System.out.println(proxyPersion.hashCode());

    }

    /**
     * 为了避免这种死循环，我们可以使用MethodInterceptor，MethodInterceptor的例子在前面的hello world中已经介绍过了，这里就不浪费时间了。
     */
    public static void testInvocationHandler(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return "hello cglib";
                }else {
                    throw  new RuntimeException("do not know what to do.");
                }
            }
        });
        Person proxyPerson = (Person)enhancer.create();
        System.out.println(proxyPerson.say("hi"));
        System.out.println(proxyPerson.toString());

    }

    /**
     * 有些时候我们可能只想对特定的方法进行拦截，对其他的方法直接放行，不做任何操作，使用Enhancer处理这种需求同样很简单,只需要一个CallbackFilter即可
     */
    public static void testCallbackFilter(){
        Enhancer enhancer = new Enhancer();
        CallbackHelper callbackHelper = new CallbackHelper(Person.class, new Class[0] ) {
            @Override
            protected Object getCallback(Method method) {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class){
                    return new FixedValue() {
                        @Override
                        public Object loadObject() throws Exception {
                            return "hello cglib";
                        }
                    };
                }else {
                    return NoOp.INSTANCE;
                }
            }
        };

        enhancer.setSuperclass(Person.class);
        enhancer.setCallbackFilter(callbackHelper);
        enhancer.setCallbacks(callbackHelper.getCallbacks());

        Person proxyPerson = (Person)enhancer.create();
        System.out.println(proxyPerson.say("ha"));
        System.out.println(proxyPerson.toString());
        System.out.println(proxyPerson.hashCode());

    }

    /**
     * 生成一个不可变的代理对象.
     * 后面调用set方法改值会拋出异常
     */
    public static void testImmutableBean(){
        Person person = new Person();
        person.setName("tianxiong");

        Person immutablePerson = (Person)ImmutableBean.create(person);
        System.out.println(person.getName());
        System.out.println(immutablePerson.getName());
        person.setName("chenhaiying");
        immutablePerson.setName("chenhaiying");

    }

    /**
     * 动态创建一个bean.
     * 无中生有创建一个java类型
     */
    public static void testBeanGenerator() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BeanGenerator beanGenerator = new BeanGenerator();
        // 添加一个属性,名叫value,类型为String. 同时会有get, set方法
        beanGenerator.addProperty("value", String.class);
        Object myBean = beanGenerator.create();
        Method setter = myBean.getClass().getMethod("setValue", String.class);
        setter.invoke(myBean,"test");

        Method getter = myBean.getClass().getMethod("getValue");
        String result = (String)getter.invoke(myBean);
        System.out.println(result);

    }

    /**
     * 属性复制
     */
    public static void testBeanCopier(){
        // 设置为true,则使用converter
        BeanCopier beanCopier = BeanCopier.create(Person.class, Person2.class, false);
        Person person = new Person();
        person.setName("tian");

        Person2 person2 = new Person2();
        beanCopier.copy(person,person2,null);
        System.out.println(person2.getName());

    }

    /**
     * bean的属性值转成map
     */
    public static void testBeanMap(){
        Person person = new Person();
        person.setName("tian");
        person.setAge(30);

        BeanMap beanMap = BeanMap.create(person);
        for(Object s:beanMap.keySet()){
            System.out.println(s+" : "+beanMap.get(s));
        }
    }
}
