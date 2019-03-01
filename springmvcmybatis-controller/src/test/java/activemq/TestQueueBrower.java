package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;
import java.util.Enumeration;

/**
 * 有些业务场景下我们需要查看队列中都有哪些消息, 但是查看本身又要求只看内容,不影响其它的服务的正常消费
 *
 * Created by tianxiong on 2019/2/28.
 */
public class TestQueueBrower {
    /**
     * 默认用户名
     */
    private static final String USERNAME = "admin";
    /**
     * 默认密码
     */
    private static final String PASSWORD = "admin";
    /**
     * 默认连接地址
     */
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) throws JMSException {
        //实例化连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,"failover://tcp://118.126.115.206:61616");
        Connection connection = connectionFactory.createConnection();
        // 创建连接对象后, 调用start方法才真正连接
        connection.start();
        Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);

        Queue queue = new ActiveMQQueue("jd_dish_queue");
        QueueBrowser browser = session.createBrowser(queue);
        Enumeration<TextMessage> enumeration = browser.getEnumeration();
        while (enumeration.hasMoreElements()){
            TextMessage message = enumeration.nextElement();
            System.out.println("查看, 不消费消息: "+message.getText());
        }


    }
}
