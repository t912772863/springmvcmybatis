package activemq;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * 消息生产者: 点对点模式
 * Created by tian on 2016/11/1.
 */
public class JMSProducer {
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
            //创建方,
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //创建消息队列
            destination = session.createQueue("Queue139");
            //创建消息生产者
            messageProducer = session.createProducer(destination);

            // 可以设置mq的持久化方案,可以不持久, 也可持久化到硬盘或者数据库中
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

//            sendMessage(session,messageProducer);
            sendMessage2(session,messageProducer);

            // 创建session的时候添加的有事务,所以这里要commit
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null){
                try{
                    // 释放连接
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * MQ中定义了五种类型的消息
     * StreamMessage  java原始值的数据流
     * MapMessage 一套名称--值对
     * TextMessage  一个字符串对象
     * ObjectMessage  一个序列化的java对象
     * BytesMessage 一个未解释字节的数据流
     *
     *
     * @param session
     * @param producer
     * @throws Exception
     */
    public static void sendMessage(Session session,MessageProducer producer) throws Exception{
        for(int i = 0;i<JMSProducer.NUMBER;i++){
            TextMessage message = session.createTextMessage("{ \"code\": \"S_OK\", \"summary\": \"\",\"var\": [ {\"smsId\": \"123\",\"receiveNumber\": \"13510272496\" }, {\"smsId\": \"124\", \"receiveNumber\": \"13510272496\" } ]}");
            System.out.println("发送消息: "+"activemq发送的消息"+i);
            /*
            send发送消息的方法有很多重载的方法
            在上面的code当中，我们创建生产者的时候，指定了Destination，设置了持久化方式，实际上这些都可以不必指定的，而是到send的时候指定。而且在实际业务开发中，往往根据各种判断，来决定将这条消息发往哪个Queue，因此往往不会在MessageProducer创建的时候指定Destination。
            TTL，消息的存活时间，一句话：生产者生产了消息，如果消费者不来消费，那么这条消息保持多久的有效期
            priority，消息优先级，0-9。0-4是普通消息，5-9是加急消息，消息默认级别是4。注意，消息优先级只是一个理论上的概念，并不能绝对保证优先级高的消息一定被消费者优先消费！也就是说ActiveMQ并不能保证消费的顺序性！
            deliveryMode，如果不指定，默认是非持久化的消息。如果可以容忍消息的丢失，那么采用非持久化的方式，将会改善性能、减少存储的开销。
             */
            producer.send(message,DeliveryMode.PERSISTENT,4,1000*60);
        }
    }

    /**
     * 测试消息选择器的方法
     * @param session
     * @param producer
     * @throws Exception
     */
    public static void sendMessage2(Session session,MessageProducer producer) throws Exception{
        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("name","zhangsan");
        mapMessage.setInt("age", 28);

        MapMessage mapMessage2 = session.createMapMessage();
        mapMessage2.setString("name","lisi");
        mapMessage2.setInt("age",18);

        producer.send(mapMessage, DeliveryMode.PERSISTENT,4,-1);
        producer.send(mapMessage2, DeliveryMode.PERSISTENT,4,-1);

    }

}
