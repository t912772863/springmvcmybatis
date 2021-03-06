package com.tian.springmvcmybatis.controller.common;
import com.tian.springmvcmybatis.dao.entity.Activity;
import com.tian.springmvcmybatis.service.IActivityService;
import com.tian.springmvcmybatis.service.common.InnerConstant;
import com.tian.springmvcmybatis.service.common.TimerTask;
import com.tian.common.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.PostConstruct;

/**
 * redis中key事件监听器
 * Created by Administrator on 2016/12/15 0015.
 */
@Component
public class RedisListener extends JedisPubSub {

    @Autowired
    IActivityService activityService;

    @PostConstruct
    public void init(){
        final RedisListener listener = this;
        new Thread(new Runnable() {
            public void run() {
                Jedis jedis = JedisUtil.getResource();
                //__keyevent@0__:expired
                //  键事件,1号数据库, 过期事件
                jedis.psubscribe(listener, "__keyevent@1__:expired");
                JedisUtil.returnResource(jedis);
            }
        }).start();

    }

    // 取得订阅的消息后的处理
    public void onMessage(String channel, String message) {
        System.out.println(channel + "=" + message);
    }

    // 初始化订阅时候的处理
    public void onSubscribe(String channel, int subscribedChannels) {
         System.out.println(channel + "=" + subscribedChannels);
    }

    // 取消订阅时候的处理
    public void onUnsubscribe(String channel, int subscribedChannels) {
         System.out.println(channel + "=" + subscribedChannels);
    }

    // 初始化按表达式的方式订阅时候的处理
    public void onPSubscribe(String pattern, int subscribedChannels) {
         System.out.println(pattern + "=" + subscribedChannels);
    }

    // 取消按表达式的方式订阅时候的处理
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
         System.out.println(pattern + "=" + subscribedChannels);
    }

    /**
     *  取得按表达式的方式订阅的消息后的处理
     *
     *  注意的是该方法内如果出现异常,一定要捕捉,不能拋出,否则会导致监视键过期的线程死掉
     * @param pattern
     * @param channel
     * @param message
     */
    public void onPMessage(String pattern, String channel, String message) {
        try{
            System.out.println("------------------>" +message);
            // 这里的message就是设置的key
            if(message.startsWith("sendMessage")){
                // 发送消息任务开始
                TimerTask.doSend(message);
            }else {
                // 活动状态变迁
                String[] strArr = message.split("_");
                Activity activity = new Activity();
                activity.setId(Long.parseLong(strArr[0]));
                activity.setActivityStatus(Integer.parseInt(strArr[1]));

                // 更新状态
                activityService.updateActivityById(activity);
                Activity activity1 = activityService.queryById(Long.parseLong(strArr[0]));
                //如果是活动开始,把消息推送给所有的用户,如果是活动结束,则把消息推送给发起人
                if(Integer.parseInt(strArr[1]) == InnerConstant.ACTIVITY_STATUS_DOING){
                    MessagePush.push(activity1.getName()+"活动开始啦,快来围观吧","channel_all");
                }else if(Integer.parseInt(strArr[1]) == InnerConstant.ACTIVITY_STATUS_OVER){
                    MessagePush.push(activity1.getName()+"活动圆满结束","channel_"+activity1.getId());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            // 处理失败的逻辑,一定不要拋出新的异常
        }

    }
}
