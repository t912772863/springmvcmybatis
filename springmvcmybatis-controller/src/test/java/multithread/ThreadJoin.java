package multithread;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
public class ThreadJoin {
    public static void main(String[] args) {
        System.out.println("main线程开始");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1开始运行");
                System.out.println("thread1运行中...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1运行结束");
            }
        });
        thread1.start();
        System.out.println("main运行中...");

        //thread1.join()方法, 表示主线程会在这里等待子线程运行完成后, 再接着往下运行.
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main线程结束");
    }
}
