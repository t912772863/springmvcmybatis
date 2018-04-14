package temp;

/**
 * 自己写一个线程池接口, 并实现线程池的基本功能.
 * Created by Administrator on 2018/3/5 0005.
 */
public interface ThreadPool<Job extends Runnable> {
    /**
     * 执行一个job, 这个job需要实现Runnable
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     * @param num
     */
    void removeWorker(int num);

    /**
     * 得到正在等待执行的任务的数量.
     * @return
     */
    int getJobSize();
}
