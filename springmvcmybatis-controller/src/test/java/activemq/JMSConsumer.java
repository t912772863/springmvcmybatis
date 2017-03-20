package activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**消息消费者
 * Created by tian on 2016/11/1.
 */
public class JMSConsumer {
    /**
     * 默认用户名
     */
    private static final String USERNAME = "admin";
    /**
     * 默认密码
     */
//    private static final String PASSWORD = "admin";
    private static final String PASSWORD = "richinfo";
    /**
     * 默认连接地址
     */
//    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String BROKEURL = "failover://tcp://192.168.8.239:61616";
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
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//消费方不加事务,所以用false
            destination = session.createQueue("Queue139");//创建连接的消息队列,要与发送消息方名字一致
            messageConsumer = session.createConsumer(destination); //创建消息消费者
            while (true){
                TextMessage textMessage = (TextMessage) messageConsumer.receive(1000);
                if(textMessage !=null){
                    System.out.println("收到的消息: "+textMessage.getText());
                    // {"createTime":"2017-03-14 12:20:59","receiveNumber":"","sendNumber":"","smsContent":"","smsId":"35489860854147ce8d0075e8a9bcf5af","state":"0","summary":"","type":"0"}
                }else {
                    break;
                }
            }
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
