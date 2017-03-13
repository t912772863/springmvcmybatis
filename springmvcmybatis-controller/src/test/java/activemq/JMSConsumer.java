package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**消息消费者: 点对点模式,不停的去查询,这种方式在生产中不建议使用
 * Created by tian on 2016/11/1.
 */
public class JMSConsumer {
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
            connection.start();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//消费方不加事务,所以用false
            destination = session.createQueue("FirstQueue1");//创建连接的消息队列,要与发送消息方名字一致
            messageConsumer = session.createConsumer(destination); //创建消息消费者
            while (true){
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
                if(textMessage !=null){
                    System.out.println("收到的消息: "+textMessage.getText());
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
