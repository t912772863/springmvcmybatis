package com.tian.springmvcmybatis.controller.common;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**activemq消息监听器
 * Created by Administrator on 2017/3/2 0002.
 */
public class QueueMessageListener implements MessageListener{

    /**
     * 收到消息时,自动调用该方法
     * @param message
     */
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener监听到了文本消息：\t"+ tm.getText());
            //do something ...
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
