package com.tian.springmvcmybatis.service.common;

import com.tian.springmvcmybatis.dao.dto.ActivityDto;
import com.tian.springmvcmybatis.dao.entity.File;
import com.tian.springmvcmybatis.dao.entity.SendMessage;
import com.tian.springmvcmybatis.service.IActivityService;
import com.tian.springmvcmybatis.service.IFileService;
import com.tian.springmvcmybatis.service.ISendMessageService;
import com.tian.springmvcmybatis.service.common.util.DateUtil;
import com.tian.springmvcmybatis.service.common.util.DocumentUtil;
import com.tian.springmvcmybatis.service.common.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 定时任务类
 * Created by Administrator on 2016/12/9 0009.
 */
@Component
public class TimerTask {

    public static final Map<String,LinkedBlockingQueue> sendMessageMap = new HashMap<String, LinkedBlockingQueue>();
    @Autowired
    IActivityService activityService;
    @Autowired
    ISendMessageService sendMessageService;
    @Autowired
    IFileService fileService;

    @PostConstruct
    public void init(){
        updateActivityStatus();
    }

//    @Scheduled(cron = "0 0/1 * * * ?")
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

    /**
     * 把快要到定时变更活动状态的数据加入到内存中,通过redis的键过期通知事件,调用方法.
     */
    @Scheduled(cron = "0 0/59 * * * ?")
    public void updateActivityStatus(){
        // 从数据库中查询时间在60分钟后就要到的
        String startTime = DateUtil.getDateAfterHour(0,"yyyy-MM-dd HH:mm:ss");
        String endTime = DateUtil.getDateAfterHour(1,"yyyy-MM-dd HH:mm:ss");
        List<ActivityDto> list = activityService.queryActivityNeedUpdateStatus(startTime,endTime);

        // 将这些数据按自定义的规则,转换成key,并根据时间设置一个过期时间,存入缓存中
             // 当前时间毫秒值
        long current = System.currentTimeMillis();
        for (ActivityDto a : list) {
            // 拼接自定义的key
            String key = a.getId()+"_"+a.getStatus();
            // 把redis库切换到1号库
            Jedis jedis = JedisUtil.getResource();
            jedis.select(1);
            // 把值放进去,同时设定一个过期时间
            int s = (int)(a.getTheTime().getTime()-current)/1000;
            jedis.setex(key,s,key);
            JedisUtil.returnResource(jedis);
        }
    }

    /**
     * 加载发送消息的号码集
     */
    @Scheduled(cron = "0 0/59 * * * ?")
    public void loadSendMessageNumber() throws IOException {
        // 从数据库中查询时间在60分钟后就要到的
        String startTime = DateUtil.getDateAfterHour(0,"yyyy-MM-dd HH:mm:ss");
        String endTime = DateUtil.getDateAfterHour(1,"yyyy-MM-dd HH:mm:ss");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("sendStartTime",startTime);
        params.put("sendEndTime",endTime);
        List<SendMessage> list = sendMessageService.querySendMessageByRule(params);

        // 把每个任务的id和对应该的文件读取出来关联
        List<String> fileList = null;
        java.io.File file = null;
        Map<String,Object> phoneMap = null;

        // 当前时间毫秒值
        long current = System.currentTimeMillis();
        for (SendMessage s : list) {
            // 根据发送消息ID查询出对应的excel文件,并把文件中的手机号读取出来放入队列中,
            fileList = fileService.queryFileByTableNameAndDataId("send_message",s.getId());
            // 因为任务和文件是一对一的,所以直接取第一个就可以了
            phoneMap = DocumentUtil.readXlsx(fileList.get(0));
            LinkedBlockingQueue queue = new LinkedBlockingQueue((List)phoneMap.get("list"));
            sendMessageMap.put("sendMessage_"+s.getId(),queue);

            // 针对每一个发送消息任务,添加倒计时到redis中
            // 拼接自定义的key
            String key = "sendMessage_"+s.getId();
            // 把redis库切换到1号库
            Jedis jedis = JedisUtil.getResource();
            jedis.select(1);
            // 把值放进去,同时设定一个过期时间
            int time = (int)(s.getSendTime().getTime()-current)/1000;
            jedis.setex(key,time,key);
            JedisUtil.returnResource(jedis);
        }
    }
}
