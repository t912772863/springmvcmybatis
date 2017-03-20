package activemq;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * 消息生产者: 订阅模式
 * Created by tian on 2016/11/1.
 */
public class JMSProducer2 {
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
    /**
     * 发送消息数量
     */
    private static final int NUMBER = 10;




    public static void main(String[] args) {
        //连接工厂
        ConnectionFactory connectionFactory;
        //连接
        Connection connection = null;
        //会话,接收或者发送消息的线程
        Session session;
        //消息目的地
        Destination destination;
        //消息生产者
        MessageProducer messageProducer;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        //获取连接
        try{
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);//创建方,要加事务,所以用true
            //创建消息队列
//            destination = session.createQueue("FirstQueue1");
            destination = session.createTopic("FirstTopic");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            sendMessage(session,messageProducer);
            // 创建session的时候添加的有事务,所以这里要commit
            session.commit();
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

    public static void sendMessage(Session session,MessageProducer producer) throws Exception{
        for(int i = 0; i< JMSProducer2.NUMBER; i++){
            TextMessage message = session.createTextMessage("activemq发送的消息"+i);
            System.out.println("发送消息: "+"activemq发送的消息"+i);
            producer.send(message);
        }
    }

}
