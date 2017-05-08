package multithread.threadpooltest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition类似于thread.wait, thread.notify. 可以实现线程之间的通讯
 * Created by Administrator on 2017/5/8 0008.
 */
public class ConditionDemo {
    public static void main(String[] args) {
        final Business business = new Business();
        // 创建一个子线程执行任务
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1;i<=50 ; i++){
                    business.sub(i);
                }
            }
        }).start();

        // main线程, 也就是主线程也执行一个任务
        for(int i = 1;i<=50 ; i++){
            business.mai(i);
        }
    }

    /**
     * 之前线程的通讯是通过syncronized和wait,nofity等方法实现的.现在通过jdk5中的Lock和Condition对象来实现
     */
    static class Business{
        /** 锁对象*/
        Lock lock = new ReentrantLock();
        /** 条件对象*/
        Condition condition = lock.newCondition();

        /** 当前是否轮到子线程执行*/
        private boolean isSub = true;
        /**
         * 主线程循环100 次的业务
         * @param i 外部循环次数
         */
        public void mai(int i){
            try{
                // 上个锁
                lock.lock();
                // 下面用while的时候, 被唤醒后还会再验证一次, 代码可靠性更高, 可以防止线程惊醒的情况
                while(isSub){
                    // 如果进来以后发现不到主线程执行的时候, 则进行等待
                    try {
                        // 注意这里是await方法, 不要写成object的wait方法了
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j = 1;j<=100;j++){
                    System.out.println("主线程外循环第 "+i+"次, 内循环第 "+j+"次");
                }
                // 主线程执行完了,改成轮到子线程
                isSub = true;
                // 同时唤醒一个其它线程, 这里除了当前线程, 只可能是子线程了
                condition.signal();
            }finally {
                // 解锁
                lock.unlock();
            }

        }

        /**
         * 子线程循环10次的业务
         * @param i 外部循环次数
         */
        public synchronized  void sub(int i){
            try{
                // 上锁
                lock.lock();
                while(!isSub){
                    // 如果进来以后发现不到子线程执行的时候, 则进行等待
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int j = 1;j<=10;j++){
                    System.out.println("子线程外循环第 "+i+"次, 内循环第 "+j+"次");
                }
                // 子线程执行完一次, 改成轮到主线程
                isSub = false;
                // 同时唤醒一个其它线程, 这里除了当前线程, 只可能是主线程了
                condition.signal();
            }finally {
                // 解锁
                lock.unlock();
            }

        }
    }
}
