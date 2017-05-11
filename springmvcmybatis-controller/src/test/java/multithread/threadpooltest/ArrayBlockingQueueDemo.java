package multithread.threadpooltest;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 有穷阻塞队列学习
 * add放不进去会拋出异常
 * offer放不进去返回false
 * put放不进去等阻塞
 *
 * remove取不到会拋异常,
 * poll取不到数据返回null
 * take取不到数据进行等待
 *
 *
 * Created by Administrator on 2017/5/11 0011.
 */
public class ArrayBlockingQueueDemo {
    {
        // 匿名构造方法, 在任构造方法调用的时候都会调用
        System.out.println("我是匿名构造方法");
    }

    public static void main(String[] args) {
        // 创建一个大小为3的有序队列
        final ArrayBlockingQueue queue = new ArrayBlockingQueue(3);

        // 创建3个线程放数据
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true){
                        try {
                            System.out.println("线程: "+Thread.currentThread().getName()+"准备放入数据");
                            Thread.sleep((long)Math.random()*1000);
                            queue.put(1);
                            System.out.println("线程: "+Thread.currentThread().getName()+"放入数据, 当前队列大小为: "+queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }

        // 创建两个线程取数据
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true){
                        System.out.println("线程: "+Thread.currentThread().getName()+"准备取数据");
                        try {
                            Thread.sleep((long)Math.random()*1000);
                            queue.take();
                            System.out.println("线程: "+Thread.currentThread().getName()+"已经取出数据, 当前队列大小为: "+queue.size());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }

    }
}
