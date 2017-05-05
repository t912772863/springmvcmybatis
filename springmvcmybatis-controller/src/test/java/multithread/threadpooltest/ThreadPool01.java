package multithread.threadpooltest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
public class ThreadPool01 {
    public static void main(String[] args) {
        // 创建一个固定大小为3的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 给线程池添加任务
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            executorService.execute(new Runnable() {
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+",task: "+ temp +" loop of "+j);
                    }
                }
            });
        }
        System.out.println("all task had committed.");
        // 当线程中所有的任务完成后, 结束所有线程
        executorService.shutdown();

        // 立刻结束线程池中所有的线程,不论任务是否完成.
//        executorService.shutdownNow();

        // 缓存线程池, 可以根据当前任务的多少自动调整线程的多少
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        // 单个线程池, 只有一个线程, 挂掉后会自动重启
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();
    }
}
