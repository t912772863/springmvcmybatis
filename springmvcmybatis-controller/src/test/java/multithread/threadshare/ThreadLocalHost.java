package multithread.threadshare;

import java.util.Random;

/**
 * jdk自带的线程内共享变量的类, 可以实现与前面相似的功能
 * Created by Administrator on 2017/5/1 0001.
 */
public class ThreadLocalHost {
    /** 线程内共享变量,
     *  对于线程中有多个共享变量的, 可以封闭一个实体放入泛型中
     * */
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
    public static void main(String[] args) {
        for (int i = 0; i <2 ; i++) {
            new Thread(new Runnable() {
                public void run() {
                    int value = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()+" has put value: "+value);
                    // 直接调用set方法 , 则该值就是与线程相关的
                    threadLocal.set(value);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }
    static class  A{
        public void get(){
            // 直接get得到的就是与线程相关的值
            int value = threadLocal.get();
            System.out.println("class A, thread "+Thread.currentThread().getName()+" value: "+value);
        }
    }

    static class  B{
        public void get(){
            // 直接get得到的就是与线程相关的值
            int value = threadLocal.get();
            System.out.println("class B, thread "+Thread.currentThread().getName()+" value: "+value);
        }
    }
}
