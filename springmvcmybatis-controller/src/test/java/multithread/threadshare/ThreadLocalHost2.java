package multithread.threadshare;

import java.util.Random;

/**
 * 线程内共享变量, 加强封装版
 *
 * 与前面的实现相似, 本示例主要在于对线程内共享变量这块逻辑进行了封装, 使得调用起来可以
 * 不用关注内部
 * Created by Administrator on 2017/5/1 0001.
 */
public class ThreadLocalHost2 {
    public static void main(String[] args) {
        for (int i = 0; i <2 ; i++) {
            new Thread(new Runnable() {
                public void run() {
                    int value = new Random().nextInt(10);
                    System.out.println("thread: "+Thread.currentThread().getName()+" has put "+value);
                    // 获取到与线程相关的单例
                    ThreadData threadData = ThreadData.getThreadInstance();
                    threadData.setAge(value);
                    threadData.setName("name"+value);

                    //
                    new A().get();
                    new B().get();
                }
            }).start();
        }

    }
    static class A{
        public void get(){
            System.out.println("class A, thread "+Thread.currentThread().getName()+" get data: "+ThreadData.getThreadInstance().toString());
        }

    }
    static class B{
        public void get(){
            System.out.println("class B, thread "+Thread.currentThread().getName()+" get data: "+ThreadData.getThreadInstance().toString());
        }
    }
}

/**
 * 线程共享数据封装类
 */
class ThreadData{
    private int age;
    private String name;

    /** 把自定义的线程共享数据类放入到jdk自带的线程范围有效数据类ThreadLocal中*/
    private static ThreadLocal<ThreadData> threadLocal = new ThreadLocal<ThreadData>();

    private ThreadData(){}

    /**
     * 扩展单例方法,
     *
     * 普通的单例返回的是同一个对象,该 单例返回的是线程内同一个对象, 换一个线程换一个对象
     * @return
     */
    public static ThreadData getThreadInstance(){
        ThreadData instance = threadLocal.get();
        if( instance == null){
            instance = new ThreadData();
            threadLocal.set(instance);
        }
        return instance;
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
        return "ThreadData{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
