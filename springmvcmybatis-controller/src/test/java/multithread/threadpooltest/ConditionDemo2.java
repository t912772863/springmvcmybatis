package multithread.threadpooltest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程(多个线程)间通过condition实现调度
 * Created by Administrator on 2017/5/8 0008.
 */
public class ConditionDemo2 {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 7; i++) {
                    business.do1(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 7; i++) {
                    business.do2(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 7; i++) {
                    business.do3(i);
                }
            }
        }).start();

    }
    static class  Business{
        /** 锁对象*/
        Lock lock = new ReentrantLock();
        /** 通讯对象1*/
        Condition condition1 = lock.newCondition();
        /** 通讯对象2*/
        Condition condition2 = lock.newCondition();
        /** 通讯对象3*/
        Condition condition3 = lock.newCondition();
        /** 默认1, 也就是每一个任务可以运行*/
        int temp = 1;

        public void do1(int j){
            try{
                lock.lock();

                // 上锁
                lock.lock();
                while(temp != 1){
                    // 如果没轮到自己, 则进行让步等待
                    try {
                        condition1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("-do1 内循环: "+i+", 外循环: "+j);
                }
                // 正常执行完成后, 则唤醒下一个执行的线程
                temp = 2;

                /*
                  需要注意的一点是, 本示例虽然每个线程都循环了7次, 不过由于下面这一行代码,
                  也就是唤醒线程的方法是在lock.unlock方法之前, 那么就会导致唤醒的线程, 进入
                  lock.lock方法时, 发现锁是锁着的, 会进行等待, 直到下一个lock.unlock方法执行,
                  导致的结果就是do2和do3方法的最后一次循环, 会卡在lock.lock方法那里, 所以不
                  会输出这两个方法的最后一次循环内容. 而线程也不会结束, 俗称死锁, 需要外部
                  条件来打破.
                 */
                condition2.signal();
            }finally {
                lock.unlock();
            }

        }
        public void do2(int j){
            try{
                // 上锁
                lock.lock();
                while(temp != 2){
                    // 如果没轮到自己, 则进行让步等待
                    try {
                        condition2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int i = 0; i < 20; i++) {
                    System.out.println("--do2 内循环: "+i+", 外循环: "+j);
                }
                // 正常执行完成后, 则唤醒下一个执行的线程
                temp = 3;
                /*
                 condition.signal方法, 只能写在lock内部, 也就是只能唤醒一个本线程上的等待对象,
                 如果不存在则会抛出异常.
                 */
                condition3.signal();

            }finally {
                lock.unlock();
            }

        }
        public void do3(int j){
            try{
                lock.lock();

                if(temp != 3){
                    try {
                        condition3.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 30; i++) {
                    System.out.println("---do3 内循环: "+i+", 外循环: "+j);
                }

                temp = 1;
                condition1.signal();
            }finally {
                lock.unlock();
            }
        }
    }
}
