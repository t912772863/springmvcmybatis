package activemq;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**消费监听器: 发布订阅模式
 * Created by tian on 2016/11/1.
 */
public class Listener2 implements MessageListener{
    public void onMessage(Message message) {
        try{
            System.out.println("收到消息: "+((TextMessage)message).getText());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
