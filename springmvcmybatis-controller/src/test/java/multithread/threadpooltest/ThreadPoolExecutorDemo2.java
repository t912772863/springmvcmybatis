package multithread.threadpooltest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
public class ThreadPoolExecutorDemo2 {
    public static void main(String[] args) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.CallerRunsPolicy());

        // 不用main线程, 而是再嵌套一层线程, 查看超出后由哪个线程运行超出的任务
        /*
         验证得知, 当任务添加过快的时候, 来不及处理, 如果策略用的是CallerRunsPolicy
         则会调用添加任务的那个线程(注意: 不一定就是main线程)去处理那个没有成功添加进去的任务
         这样的做法不会丢弃任务, 同时也可以防止添加过快, 给线程池处理任务留出时间
         */
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    final int temp = i;
                    System.out.println("线程: "+Thread.currentThread().getName()+"准备创建第"+i+" 个任务");
                    Runnable runnable = new Runnable() {
                        public void run() {
                            System.out.println("线程:"+Thread.currentThread().getName()+"第"+temp+"个任务开始运行了...");
                            try{
                                Thread.sleep((long)Math.random()*10000+200);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            System.out.println("线程:"+Thread.currentThread().getName()+"第"+temp+"个任务结束.");
                        }
                    };
                    // 把任务添加到线程池中
                    threadPoolExecutor.execute(runnable);
                }
                threadPoolExecutor.shutdown();
            }
        }).start();





    }
}
