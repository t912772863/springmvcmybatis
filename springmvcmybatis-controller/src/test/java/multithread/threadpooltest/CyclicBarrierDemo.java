package multithread.threadpooltest;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多个线程并发运行, 然后到某一点集合, 再开始分别运行
 *
 *
 * Created by Administrator on 2017/5/9 0009.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        // 创建循环障碍
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                public void run() {
                    try{
                        Thread.sleep(1000);
                        System.out.println("线程: "+Thread.currentThread().getName()+"将要到达集合点1, 现在有"+(cyclicBarrier.getNumberWaiting()+1)
                            +"在那里等了.");
                        cyclicBarrier.await();


                        Thread.sleep((long)Math.random()*10000);
                        System.out.println("线程: "+Thread.currentThread().getName()+"将要到达集合点2, 现在有"+(cyclicBarrier.getNumberWaiting()+1)
                                +"在那里等了.");
                        cyclicBarrier.await();

                        Thread.sleep((long)Math.random()*10000);
                        System.out.println("线程: "+Thread.currentThread().getName()+"将要到达集合点3, 现在有"+(cyclicBarrier.getNumberWaiting()+1)
                                +"在那里等了.");
                        cyclicBarrier.await();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }


    }


}
