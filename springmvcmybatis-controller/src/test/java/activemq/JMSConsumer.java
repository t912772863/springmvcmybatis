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
    private static final String USERNAME = "admin";
    /**
     * 默认密码
     */
    private static final String PASSWORD = "admin";
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
            /*
            消费方不加事务,所以用false, 如果不在事务中用了true, 则会导致消息一直消息不掉
            消息确认机制:
            AUTO_ACKNOWLEDGE：表示在消费者receive消息的时候自动的签收
            CLIENT_ACKNOWLEDGE：表示消费者receive消息后必须手动的调用acknowledge()方法进行签收
            DUPS_OK_ACKNOWLEDGE：签不签收无所谓了，只要消费者能够容忍重复的消息接受
             */
            session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("Queue139");//创建连接的消息队列,要与发送消息方名字一致
            messageConsumer = session.createConsumer(destination); //创建消息消费者

//            getMessage(messageConsumer);
            getMessage2(session,destination);
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

    public static void getMessage(MessageConsumer consumer) throws Exception{
        while (true){
            TextMessage textMessage = (TextMessage) consumer.receive(1000);
            if(textMessage !=null){
                System.out.println("收到的消息: "+textMessage.getText());
            }else {
                break;
            }
            // 非自动确认模式的时候,调用该方法,确认签收消息, 否则消息一直有效
            textMessage.acknowledge();
        }
    }

    public static void getMessage2(Session session, Destination destination) throws JMSException {
        String condition = "age >= 20";
        MessageConsumer consumer = session.createConsumer(destination,condition);
//        MessageConsumer consumer = session.createConsumer(destination);
        while (true){
            MapMessage mapMessage = (MapMessage) consumer.receive(1000*10);
            if(mapMessage !=null){
                System.out.println("收到的消息: "+mapMessage.getString("name")+", "+mapMessage.getInt("age"));
            }else {
                break;
            }
            // 非自动确认模式的时候,调用该方法,确认签收消息, 否则消息一直有效
            mapMessage.acknowledge();
        }
    }
}
