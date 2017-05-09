package multithread.threadpooltest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时线程工具, 可以模拟赛跑类游戏
 * Created by Administrator on 2017/5/9 0009.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        // 命令最后一下的倒计时
        final CountDownLatch cdOrder = new CountDownLatch(1);
        // 三个运动员
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            final int temp = i;
            Runnable runnable = new Runnable() {
                public void run() {
                    System.out.println("运动员"+temp+"已经准备好了");
                    try {
                        cdOrder.await();
                        System.out.println("运动员"+temp+"已经出发");
                        Thread.sleep((long)Math.random()*1000);
                        System.out.println("运动员"+temp+"已经到终点了");
                        cdAnswer.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            };
            service.execute(runnable);
        }
        System.out.println("裁判准备下达命令");

        System.out.println("裁判已经下达命令");
        cdOrder.countDown();

        System.out.println("裁判在等待比赛结果...");
        try {
            cdAnswer.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("裁判看到所有人到了, 比赛结束.");

    }

}
