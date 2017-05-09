package multithread.threadpooltest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号灯类使用示例:
 * 可以实现对某一个资源同时限定访问的线程个数.
 * 比如初始化3个信号灯, 来一个线程取走一个, 当3个信号灯全部取走的时候,
 * 再来线程就只能等待了, 而如果某个线程完成了操作,离开的时候会把信号
 * 灯还回来, 这样就又有一个线程可以获取到灯进行访问了.
 *
 *
 * Created by Administrator on 2017/5/9 0009.
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        // Semaphore还有另一个构造方法, 传多一个boolean参数, 该参数表示是否公平的
        // 意思, 如果为true, 则先进来的线程, 先获取到灯, 否则为随机的.
        final Semaphore semaphore = new Semaphore(3,true);
        for (int i = 0; i < 10 ; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    // 获取一个信号灯
                    try {
                        semaphore.acquire();
                        System.out.println("线程: "+Thread.currentThread().getName()+" 进入, 当前已有"+(3-semaphore.availablePermits())+"个并发.");
                        Thread.sleep(1000);
                        System.out.println("线程: "+Thread.currentThread().getName()+" 将要离开了.");
                        semaphore.release();
                        System.out.println("线程: "+Thread.currentThread().getName()+" 已经离开了, 当前还有"+(3-semaphore.availablePermits())+"个并发.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            service.execute(runnable);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }

}
