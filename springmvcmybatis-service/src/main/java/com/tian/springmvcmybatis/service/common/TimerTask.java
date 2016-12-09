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
        System.out.println("--------------定时任务运行了");
    }
}
