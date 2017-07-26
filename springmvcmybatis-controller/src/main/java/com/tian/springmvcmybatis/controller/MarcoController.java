package com.tian.springmvcmybatis.controller;

import com.tian.springmvcmybatis.controller.dto.ShoutDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * 通过@MessageMapping接收webSocket消息
 *
 * 这种方式一般是由web发起一个消息,然后后台再异步的一对一或者一对多的通知页面.
 * Created by Administrator on 2017/7/25 0025.
 */
@Controller
public class MarcoController {

    /**
     * 处理发往"/app/marco"目的地的消息
     *
     * 纯接收消息
     * @param incoming
     */
    @MessageMapping("marco")
    public void handleShout(ShoutDto incoming){
        System.out.println("收到消息: "+incoming.getMessage());
    }

    /**
     * 响应消息
     * @return
     */
    @MessageMapping("marco2")
    // 把本方法返回的消息,发送到目的地"/topic/shout", 则所有订阅了这个主题的用户都会收到这条消息
    @SendTo("/topic/shout")
    public ShoutDto handleShout2(){
        ShoutDto outgoing = new ShoutDto();
        outgoing.setMessage("Polo!");
        return outgoing;
    }

    @MessageMapping("sendToUser")
    @SendToUser("/queue/notifications")
    public ShoutDto sendToUser(){
        ShoutDto shoutDto = new ShoutDto();
        shoutDto.setMessage("only for you!");
        return shoutDto;
    }

}
