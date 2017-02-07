package com.tian.springmvcmybatis.service.common;

import com.tian.springmvcmybatis.dao.dto.ActivityDto;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 定时任务类
 * Created by Administrator on 2016/12/9 0009.
 */
@Component
public class TimerTask {

    /**
     * 发送信息任务的数据
     */
    public static final Map<String,Runnable> sendMessageMap = new HashMap<String, Runnable>();
    /**
     * 发送信息任务的开关
     */
    public static final Map<String,Boolean> switchMap = new HashMap<String, Boolean>();
    /**
     * 固定大小为5的线程池来处理发送消息任务
     */
    public static final ExecutorService executor = Executors.newFixedThreadPool(5);
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
//    @Scheduled(cron = "0 0/59 * * * ?")
    @Scheduled(cron = "0 0/1 * * * ?")
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
        Map<String,Object> phoneMap = null;

        // 当前时间毫秒值
        long current = System.currentTimeMillis();
        for (SendMessage s : list) {
            // 根据发送消息ID查询出对应的excel文件,并把文件中的手机号读取出来放入队列中,
            fileList = fileService.queryFileByTableNameAndDataId("send_message",s.getId());
            // 因为任务和文件是一对一的,所以直接取第一个就可以了
            String filePath = fileList.get(0);
            // 数据库存的是页面的访问路径,这里要转换成真实的物理路径
            filePath = "D:/tomcat/tomcatFile/"+filePath.substring(5);
            phoneMap = DocumentUtil.readXlsx(filePath);
            final LinkedBlockingQueue queue = new LinkedBlockingQueue((List)phoneMap.get("list"));

            /*
            创建一个任务runnable,把任务交给线程池去做.所以要用到匿名内部类
            但是要注意一点的是,匿名内部类是没有带参数的构造方法的,而实际业务中,有参数要传递
            两种方式解决这个问题:
            1. 新建一个类实现runnable接口,在这个类中添加有参构造方法,把参数传进来(但是这个方法有一个缺点就是
                会造成类的膨胀,因为类只会用一次)
            2. 还是用匿名内部类,不过除了必要的run方法以外,如下,还添加了一个自定义的方法,用来获取参数,并赋值给成员变量,
               这样run方法内,就可以调用参数了.
             */
            Runnable runnable = new Runnable() {
                private String id;
                private LinkedBlockingQueue queue;
                public void run() {
                    System.out.println("------------->开始发送消息了");
                    while(TimerTask.switchMap.get(id)){
                        Object obj = queue.poll();
                        if(obj == null){
                            TimerTask.switchMap.put(id,new Boolean(false));
                            return;
                        }
                        System.out.println(obj+"发送成功");
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // 匿名内部类用来和外部沟通参数的方法
                private Runnable getParams(String id,LinkedBlockingQueue queue){
                    this.id = id;
                    this.queue = queue;
                    return this;
                }
            }.getParams("sendMessage_"+s.getId(),queue);
            // 任务数据
            sendMessageMap.put("sendMessage_"+s.getId(),runnable);
            // 任务开关
            switchMap.put("sendMessage_"+s.getId(),new Boolean(true));

            // 针对每一个发送消息任务,添加倒计时到redis中
            // 拼接自定义的key
            String key = "sendMessage_"+s.getId();
            // 把redis库切换到1号库
            Jedis jedis = JedisUtil.getResource();
            // 把值放进去,同时设定一个过期时间
            int time = (int)(s.getSendTime().getTime()-current)/1000;
            jedis.select(1);

            jedis.setex(key,time,key);
            JedisUtil.returnResource(jedis);
        }

    }

    public static void doSend(String id){
        Runnable runnable = sendMessageMap.get(id);
        if(switchMap.get(id)){
            executor.execute(runnable);
        }
    }
}
