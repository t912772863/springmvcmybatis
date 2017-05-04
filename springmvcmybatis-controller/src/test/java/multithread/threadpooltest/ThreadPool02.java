package multithread.threadpooltest;

import com.tian.springmvcmybatis.dao.entity.SystemMenu;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * jdk5中的线程池实现的定时任务
 * Created by Administrator on 2017/5/3 0003.
 */
public class ThreadPool02 {
    public static void main(String[] args) {
        /*
        创建一个大小为3的线程池, 传入一个任务,
        5秒后第一次执行, 以后每隔3秒执行一次, 每次执行这个任务的线程可能不同, 但都属于这个线程池中的.
         */
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
            public void run() {

                System.out.println(Thread.currentThread().getName()+": bombing!!!   current time: "+ System.currentTimeMillis());
            }
        }, 5,3, TimeUnit.SECONDS);


    }
}
