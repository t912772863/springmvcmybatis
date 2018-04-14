package temp;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 *
 * Created by Administrator on 2018/3/9 0009.
 */
public class ForkJoinTest extends RecursiveTask<Integer>{
    /**
     * 阈值
     */
    private static final int HTRESHOLD = 2;
    private static final long serialVersionUID = 7153901505524054318L;

    private int start;
    private int end;

    public ForkJoinTest(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果任务足够小就计算
        boolean canCompute = (end -start) <= HTRESHOLD;
        if(canCompute){
            for (int i = start; i<= end; i++){
                sum += i;
            }
        }else {
            // 如果任务大于阈值, 就分解成两个任务
            int middle = (start + end)/2;
            ForkJoinTest leftTask = new ForkJoinTest(start, middle);
            ForkJoinTest rightTask = new ForkJoinTest(middle +1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完成, 得到结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;

        }
        return sum;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务,计算 1到4的和
        ForkJoinTest task = new ForkJoinTest(1,100000);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        System.out.println(result.get());

    }
}
