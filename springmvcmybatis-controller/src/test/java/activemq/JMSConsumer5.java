package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQPrefetchPolicy;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;

/**消息消费者: 点对点模式,不停的去查询,这种方式在生产中不建议使用.
 *
 * 消息签收的4种模式.
 * 1. 自动签收, 消费者接收一次就完了,不会再次收到该消息.
 * 2. 手动签收, 需求消费者显示的调用签收方法.
 * 3. JMS事务, 在没有异常时自动签收,
 * 4. XA 二阶段提交, 这个模式性能会比较低下, 而且并不是所有的组件都正确实现了该协议,不建议使用.
 *
 * 最佳实践是使用JMS或者手动签收的方式, 加上消息判重逻辑. (如果操作是幂等的也可以忽略), 也可以只对重复推送的消息做重复逻辑处理
 * Created by tian on 2016/11/1.
 */
public class JMSConsumer5 {
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
        ActiveMQConnectionFactory connectionFactory;
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
        // 消费者获取消息策略
        ActiveMQPrefetchPolicy activeMQPrefetchPolicy = new ActiveMQPrefetchPolicy();
        // 设置重新推送消息时延时
        connectionFactory.setRedeliveryPolicy(new RedeliveryPolicy());

        /*
        这里是设置一个消息者一次从服务端获取的消息个数.
        这个值的设定要适当, 既不能太大, 如果太多, 比如1000条, 可能会出现10个节点只有一个节点在一条条处理数据, 而其它9个节点都傻等着.
        最好也不要设置的太小, 因为每次获取消息都是网络交互, 如果消息本身特别多, 会有一点性能影响
         */
        activeMQPrefetchPolicy.setQueuePrefetch(10);
        connectionFactory.setPrefetchPolicy(activeMQPrefetchPolicy);

        //获取连接
        try{
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
            destination = session.createQueue("Queue139");//创建连接的消息队列,要与发送消息方名字一致
            messageConsumer = session.createConsumer(destination);

            getMessage(messageConsumer);
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

    /**
     * 验证一个消费者, 多线程并发取, 会出现异常.
     * @param consumer
     * @throws Exception
     */
    public static void getMessage(MessageConsumer consumer) throws Exception{
        // 下面如果是多线程会有异常.
        for(int i =0;i<3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try{
                            /*
                             多线程并发取的时候会有异常.
                             这是因为每个线程必须有一个会话。JMS合同是一个线程一次只使用一个会话 - 如果您使用的是消费者，则意味着如果使用相同的会话，
                             只有一个消费者可以一次接收消息。因此，如果您希望并发使用消息，则需要为每个使用者使用不同的会话。
                             或者简单来说, receiver方法是线程不安全的.
                              */
                            TextMessage textMessage = (TextMessage) consumer.receive(1000);
                            if(textMessage != null){
                                // 本次消息是否为重复推送(第一次推送后没有签收)
                                boolean idx = textMessage.getJMSRedelivered();
                                System.out.println("是否重复推送: "+idx);
                                System.out.println("收到的消息: "+textMessage.getText());
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }
        Thread.sleep(Integer.MAX_VALUE);
    }


}
