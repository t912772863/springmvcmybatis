package multithread.threadpooltest;

import java.util.Random;
import java.util.concurrent.*;

/**
 * callable与future的ipfi
 * Created by Administrator on 2017/5/4 0004.
 */
public class CallableAndFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // future对象为提交的任务完成后返回的值对象, 调用get方法得到的就是任务的返回值
        Future<String> future = executorService.submit(new Callable() {
            public String call() throws Exception {
                Thread.sleep(2000);
                System.out.println("callable执行完成, 准备返回值: success.");
                return "success";
            }
        });
        System.out.println("任务已经提交, 等待返回结果中...");
        String str = future.get();
        System.out.println("得到返回的值: "+str);


        // 可以一次提交多个任务, 然后,哪个先完成,先取哪个的返回值
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService2);
        for (int i = 1; i <= 10 ; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                    Thread.sleep(new Random().nextInt(5000));
                    return seq;
                }
            });
            
        }

        // 获取返回值,哪个先完成,就先获取到哪一个
        for(int i =0 ;i<10;i++){
            System.out.println(completionService.take().get());
        }

    }
}
