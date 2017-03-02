package com.tian.springmvcmybatis.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**activemq消息生产者
 * Created by Administrator on 2017/3/2 0002.
 */
@Component
public class ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 向指定队列发送消息
     * @param destinon
     * @param message
     */
    public void sendMessage(Destination destinon, final String message){
        System.out.println("向队列"+destinon.toString()+"发送了消息:"+message);
        jmsTemplate.send(destinon, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

    public void sendMessage(final String message){
        String destion = jmsTemplate.getDefaultDestination().toString();
        System.out.println("向队列"+destion+"发送了消息:"+message);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
