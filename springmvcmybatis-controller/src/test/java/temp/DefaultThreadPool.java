package temp;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    /**
     * 最大线程数
     */
    private static final int MAX_WORKERS_NUMBERS = 10;
    /**
     * 默认线程池线程数
     */
    private static final int DEFAULT_WORKERS_NUMBERS = 5;
    /**
     * 最小线程池工作线程数
     */
    private static final int MIN_WORKERS_NUMBERS = 1;
    /**
     * 这是一个工作列表,将会向里面插入工作.
     */
    private final LinkedList<Job> jobs = new LinkedList();
    /**
     * 工作者列表
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    /**
     * 工作者线程的数量
     */
    private int workerNumber = DEFAULT_WORKERS_NUMBERS;
    /**
     * 线程编号生成器
     */
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool(){
        initializeWorkers(DEFAULT_WORKERS_NUMBERS);
    }

    public DefaultThreadPool(int num){
        workerNumber = num > MAX_WORKERS_NUMBERS ? MAX_WORKERS_NUMBERS : num < MIN_WORKERS_NUMBERS ? MIN_WORKERS_NUMBERS : num;
        initializeWorkers(workerNumber);
    }

    /**
     * 初始化线程工作者
     * @param num
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i <num ; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-"+threadNum.incrementAndGet());
            thread.start();
        }
    }


    @Override
    public void execute(Job job) {
        if(job != null){
            // 添加一个工作,然后进行通知.
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for(Worker w:workers){
            w.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs){
            // 限制新增的worker数量不能超过最大值
            if(num + this.workerNumber > MAX_WORKERS_NUMBERS){
                num = MAX_WORKERS_NUMBERS - this.workerNumber;
            }
            initializeWorkers(num   );
            this.workerNumber += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs){
            if(num > this.workerNumber){
                throw new IllegalArgumentException("beyond workNum");
            }
            // 按照给定的数量停止worker
            int count = 0;
            while (count < num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNumber -= count;

        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }



    class Worker implements Runnable{
        /**
         * 是否工作
         */
        private volatile boolean running = true;
        @Override
        public void run() {
            while (running){
                Job job = null;
                synchronized (jobs){
                    // 如果工作者列表是空的, 就wait
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            // 感知到外部对WorkerThread的中断操作,返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }
                if(job != null){
                    job.run();
                }
            }

        }

        public void shutdown(){
            running = false;
        }
    }
}
