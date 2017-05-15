package multithread.threadpooltest;

import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 优先级队列学习
 * Created by Administrator on 2017/5/15 0015.
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        // 创建珍上优先级队列
        PriorityQueue<Task001> queue = new PriorityQueue<Task001>();
        // 创建一个线程池执行任务
        ExecutorService service = Executors.newFixedThreadPool(1);

        // 往里面放入几个任务
        Task001 task1 = new Task001(8);
        queue.add(task1);
        Task001 task2 = new Task001(7);
        queue.add(task2);
        Task001 task3 = new Task001(9);
        queue.add(task3);
        Task001 task4 = new Task001(1);
        queue.add(task4);

        // 开始取数据, 查看取出的数据的顺序
        Runnable runnable = null;
        while ((runnable = queue.poll()) != null){
            service.execute(runnable);
        }
        service.shutdown();

    }




   static class Task001 implements Runnable,Comparable{
        private int number;
        public Task001(int number){
            this.number = number;
        }


        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void run() {
            System.out.println("我是第"+number+"个任务");
        }

       public int compareTo(Object o) {
           Task001 t = (Task001)o;
           return (number - t.number) > 0? 1:((number-t.number) == 0?0:-1);
       }
   }
}
