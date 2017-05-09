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
