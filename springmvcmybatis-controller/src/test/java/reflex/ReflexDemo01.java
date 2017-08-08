package reflex;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class ReflexDemo01 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ReflexDemo01 reflexDemo = new ReflexDemo01();
        // 属性获取, 该方法,只能获取到非private的属性, 比如本示例中的test属性.
        Field[] fields = ReflexDemo01.class.getFields();
        // 该方法可以获取到所有的属性,包括private
        Field[] fields2 = ReflexDemo01.class.getDeclaredFields();

        Object o = new ReflexDemo01();
        // 这里用Object类型来包装了ReflexDemo01,但是在通过反射获取属性的时候,还是可以获取到ReflexDemo01的属性
        Field[] fields3 = o.getClass().getDeclaredFields();
        for(Field f : fields3){
            // 属性名
            System.out.println(f.getName());
            // 属性的类型
            System.out.println(f.getGenericType().toString());

            // 获取属性的值
            String fieldName = f.getName();
            String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method getMethod = o.getClass().getMethod(getMethodName, new Class[] {});
            Object value = getMethod.invoke(o, new Object[] {});
            System.out.println(value.toString());

        }

        // 方法获取
        Method[] methods = ReflexDemo01.class.getMethods();
        for (Method m : methods) {
            // 方法名称
            m.getName();
            System.out.println("方法名: "+m.getName());
            // 方法的参数
            Class<?>[] params = m.getParameterTypes();
            for (Class<?> c : params) {
                // 参数类型名称
                c.getName();
                System.out.println("参数名称:" + c.getName());
            }
        }

    }


    private int age=100;
    private String name = "tian";
    public String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ReflexDemo01{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
