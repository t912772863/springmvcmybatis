package multithread.threadpooltest;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过jdk5中的Lock锁对象, 实现与syncnized关键字相同的功能
 * Created by Administrator on 2017/5/4 0004.
 */
public class LockDemo {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    Outputter.put("tianxiong");
                }

            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true){
                    Outputter.put("chenhaiying");
                }

            }
        }).start();
    }

    static class Outputter{
        private static Lock lock = new ReentrantLock();

        public static void put(String str){
            try {
                lock.lock();
                for (int i = 0; i < str.length(); i++) {
                    Thread.sleep(new Random().nextInt(3));
                    System.out.print(str.charAt(i));
                }
                System.out.println();
            }catch (Exception e){
                e.printStackTrace();
            } finally{
                // 保证离开方法的时候一定解开锁, 否则别的对应就无法调用了
                lock.unlock();
            }

        }
    }
}
