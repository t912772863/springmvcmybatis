package com.tian.springmvcmybatis.service.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 * Created by Administrator on 2016/12/9 0009.
 */
@Component
public class TimerTask {
    @Scheduled(cron = "0 0/1 * * * ?")
    public void testTimerTask(){
        System.out.println("--------------定时任务运行开始了");
        try {
            // 等待的过程中,不影响其它方法的调用,说明定时任务是另起的线程.
            // 值得注意的一点是IDEA编辑,如果有断点,其它所有的线程也会等待.
            // 这个可能与IDEA工具本身的实现有关系.像eclipse就不会等待
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------定时任务运行结束了");
    }
}
