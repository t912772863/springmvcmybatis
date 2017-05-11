package multithread.threadpooltest;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exchanger对象, 可以交换两个线程的数据, 当一个线程运行到交换时, 如果另一个线程还没到达,
 * 则会进行等待, 另一个到达后, 交换完数据,就各自运行了.
 * Created by Administrator on 2017/5/11 0011.
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        // 创建一个交换数据用的对象
        final Exchanger exchanger = new Exchanger();
        service.execute(new Runnable() {
            public void run() {
                String data1 = "tianxiong";
                System.out.println("线程: "+Thread.currentThread().getName()+"正准备把数据"+data1+"换出去.");
                try {
                    Thread.sleep((long)Math.random()*10000+200);
                    // 拿到data1 然后换回了data2
                    String data2 = (String)exchanger.exchange(data1);

                    System.out.println("线程: "+Thread.currentThread().getName()+"换回了数据"+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        service.execute(new Runnable() {
            public void run() {
                String data1 = "chenhaiying";
                System.out.println("线程: "+Thread.currentThread().getName()+"正准备把数据"+data1+"换出去.");
                try {
                    Thread.sleep((long)Math.random()*10000+200);
                    // 拿到data1 然后换回了data2
                    String data2 = (String)exchanger.exchange(data1);

                    System.out.println("线程: "+Thread.currentThread().getName()+"换回了数据"+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


    }

}
