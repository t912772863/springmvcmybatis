package multithread.multithreadshare;

/**
 * 多线程间共享一个变量
 *
 * 下面的方法一看好像可以,
 * 但是因为自增和自减都不是原子操作, 也就是在多线程切换的时候存在并发问题.
 *
 * Created by Administrator on 2017/5/1 0001.
 */
public class MultiThreadShareData {
    private static int value = 100;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MultiThreadShareData.value ++;
                    System.out.println(Thread.currentThread().getName()+": value = "+value);
                }

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MultiThreadShareData.value ++;
                    System.out.println(Thread.currentThread().getName()+": value = "+value);
                }

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MultiThreadShareData.value --;
                    System.out.println(Thread.currentThread().getName()+": value = "+value);
                }

            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    MultiThreadShareData.value --;
                    System.out.println(Thread.currentThread().getName()+": value = "+value);
                }

            }
        }).start();
    }
}
