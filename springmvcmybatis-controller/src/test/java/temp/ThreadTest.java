package temp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**怎么实现一个线程运程的时候,则另一个方法打断运行
 * Created by Administrator on 2017/1/17 0017.
 */
public class ThreadTest {
    static boolean index = true;
    public static void main(String[]args) throws InterruptedException {
        // 生成一个任务队列
        final LinkedBlockingQueue queue = new LinkedBlockingQueue();
        for(int i = 0; i< 20 ; i++){
            queue.put(i);
        }

        // 用固定大小线程池处理任务
        ExecutorService executor = Executors.newFixedThreadPool(20);

        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    // 添加一个控制条件,可以被外部打断
                    while(index){
                        Object obj = queue.poll();
                        if(obj == null){
                            index = false;
                            return;
                        }
                        System.out.println(obj);
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executor.execute(runnable);

        Thread.sleep(10000);
        System.out.println("关掉开关");
        index = false;
        System.out.println(queue.toString());
        Thread.sleep(5000);
        System.out.println("重新打开开关");
        index = true;
        executor.execute(runnable);

    }
}
