package com.tian.springmvcmybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

/**
 * 通过注入SimpMessageSendingOperations, 实现后台主动向web页面发送消息
 * Created by Administrator on 2017/7/26 0026.
 */
@Service
public class MessageServiceImpl  {

    /**
     * 注入消息模版
     */
    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    /**
     * 给定的订阅号发送消息
     * @param topic 订阅号
     * @param message 消息内容
     */
    public void sendMessage(String topic,String message){
        simpMessageSendingOperations.convertAndSend(topic,message);
    }

    /**
     * 给特定用户发送消息
     * @param userName 用户名(可以唯一标识用户就可以)
     * @param message 消息内容
     */
    public void sendMessageToUser(String userName, String destination,Object message){
        /*
         针对特定用户的订阅, 最终会发送到路径"/user/userName/queue/notifications"上,
         但是现在的总是是在页面上无法订阅这个目的地, 待解决
         */
        simpMessageSendingOperations.convertAndSendToUser(userName, destination, message);
    }

}
