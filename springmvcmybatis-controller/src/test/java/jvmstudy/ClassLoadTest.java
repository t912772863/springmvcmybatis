package jvmstudy;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/2/6 0006.
 */
public class ClassLoadTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 自定义一个类加载器
        ClassLoader myloader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".")+1)+".class";
                InputStream in = getClass().getResourceAsStream(fileName);
                if(in == null){
                    return super.loadClass(name);
                }
                try {
                    byte[] b = new byte[in.available()];
                    in.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }


            }
        };

        Object object = myloader.loadClass("jvmstudy.ClassLoadTest").newInstance();
        System.out.println(object.getClass());
        // 两个不同的类加载器所加载的同一个class, 类型比较为false
        System.out.println(object instanceof jvmstudy.ClassLoadTest);

    }
}
