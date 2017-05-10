package multithread.threadpooltest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /*
         创建一个线程池:
         最小线程个数为2, 最大线程个数为3, 线程空闲时间为60, 单位秒
         线程池中维护了一个任务队列, 队列最大10个, 规则为公平.
         当添加任务到线程池中超出最大值时, 所采用的处理策略为CallerRunsPolicy, 也就
         是添加任务的这个线程来运行没有添加进去的任务(还有其它的几种丢弃策略).
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10, true),  new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i <= 100; i++) {
            final int temp = i;
            System.out.println("准备创建第 "+i+" 个任务");
            Runnable runnable = new Runnable() {
                public void run() {
                    System.out.println("线程: "+Thread.currentThread().getName()+"第"+temp+"个任务开始运行了...");
                    try{
                        Thread.sleep((long)Math.random()*10000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("线程: "+Thread.currentThread().getName()+"第"+temp+"个任务结束.");
                }
            };
            // 把任务添加到线程池中
            threadPoolExecutor.execute(runnable);
        }
        // 所有任务运行完成后关闭线程池.
        threadPoolExecutor.shutdown();

    }

}
