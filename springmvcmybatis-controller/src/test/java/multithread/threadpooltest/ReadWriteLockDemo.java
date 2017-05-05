package multithread.threadpooltest;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的使用.
 *
 * 读与读间不互斥
 * 读与写互斥
 * 写与写互斥
 * Created by Administrator on 2017/5/4 0004.
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        final Queue3 q3 = new Queue3();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    while (true){
                        q3.get();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    while (true){
                        q3.put(new Random().nextInt(1000));
                    }
                }
            }).start();
        }

    }

    static class Queue3{
        private Object data = null;
        private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

        /**
         * 读方法
         */
        public void get(){
            try {
                rwl.readLock().lock();
                System.out.println(Thread.currentThread().getName()+" 准备开始读了");
                Thread.sleep(new Random().nextInt(5)*1000);
                System.out.println(Thread.currentThread().getName()+"已经读完了, data = "+this.data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                rwl.readLock().unlock();
            }


        }

        /**
         * 写方法
         */
        public void put(int value){
            try{
                rwl.writeLock().lock();
                System.out.println(Thread.currentThread().getName()+"准备开始写了.");
                Thread.sleep(new Random().nextInt(5)*1000);
                this.data = value;
                System.out.println(Thread.currentThread().getName()+"写完了. data = "+value);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                rwl.writeLock().unlock();
            }
        }
    }
}
