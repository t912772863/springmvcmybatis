package spitest;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by tianxiong on 2019/3/7.
 */
public class SpiDemo {
    public static void main(String[] args) {
        /*
        ServiceLoader 是jdk自带的一个用来加载spi实现类的工具类,
        ITestSpi 是指定要加的类型.
        具体的实现类则是在约束好的路径下的以接口全路径为名字的文件中.
        如果一个接口定义了多个实现类, 都会被加载
         */
        ServiceLoader<ITestSpi> serviceLoader = ServiceLoader.load(ITestSpi.class);
        Iterator<ITestSpi> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            ITestSpi spi = iterator.next();
            System.out.println(spi.sayHello("tian"));
        }

    }
}
