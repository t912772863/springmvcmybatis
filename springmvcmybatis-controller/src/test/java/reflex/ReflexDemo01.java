package reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public class ReflexDemo01 {
    public static void main(String[] args) {
        ReflexDemo01 reflexDemo = new ReflexDemo01();
        // 属性获取
        Field[] fields = ReflexDemo01.class.getFields();
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


    private int age;
    private String name;

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
