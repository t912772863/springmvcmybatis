package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消费消费者: 点对点模式,通过一个监听器,生产中用这种方式
 * Created by tian on 2016/11/1.
 */
public class JMSConsumer4 {
    /**
     * 默认用户名
     */
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    /**
     * 默认密码
     */
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    /**
     * 默认连接地址
     */
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    public static void main(String[] args) {
        //连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //会话,接收或者发送消息的线程
        Session session;
        //消息目的地
        Destination destination;
        //消息消费者
        MessageConsumer messageConsumer;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        //获取连接
        try{
            connection = connectionFactory.createConnection();

            /*
            所谓持久化订阅，打个比方，就是说跟MQ打声招呼，即便我不在，那么给我发送的消息暂存在MQ，
            等我来了，再给我发过来。说白了，持久化订阅，需要给MQ备个案（你是谁，想在哪个Topic上搞特殊化）
             */

            // 持久化订阅第一步
            connection.setClientID("JMSConsumer4");

            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//消费方不加事务,所以用false
            destination = session.createTopic("FirstTopic");//创建连接的消息队列,要与发送消息方名字一致

            // 持久化订阅第二步
            messageConsumer = session.createDurableSubscriber((Topic)destination, "JMSConsumer4");


//            messageConsumer = session.createConsumer(destination); //创建消息消费者

            //注册一个消息监听器,一有消息就会通知
            messageConsumer.setMessageListener(new Listener2());
            Thread.sleep(100000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null){
                try{
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

    }
}
