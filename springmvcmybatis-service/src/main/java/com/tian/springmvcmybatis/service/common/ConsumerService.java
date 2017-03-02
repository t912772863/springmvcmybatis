package com.tian.springmvcmybatis.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.TextMessage;

/**
 * 消费者
 * Created by Administrator on 2017/3/2 0002.
 */
@Component
public class ConsumerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    public TextMessage receive(Destination destination){
        TextMessage textMessage = (TextMessage)jmsTemplate.receive(destination);
        try{
            System.out.println("从队列"+destination.toString()+"中收到消息: "+textMessage.getText());
        }catch (Exception e){
            e.printStackTrace();
        }
        return textMessage;
    }


}
