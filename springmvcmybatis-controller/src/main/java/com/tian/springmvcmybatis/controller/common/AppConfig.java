//package com.tian.springmvcmybatis.controller.common;
//
//import com.tian.springmvcmybatis.controller.util.KeyExpiresMessageListener;
//import org.springframework.data.redis.connection.RedisNode;
//import org.springframework.data.redis.connection.RedisSentinelConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//import org.springframework.data.redis.listener.Topic;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPubSub;
//
//import javax.annotation.PostConstruct;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Created by Administrator on 2016/12/14 0014.
// */
//@Component
//public class AppConfig {
//
//    @PostConstruct
//    public void init() {
//        System.out.println("初始化redis相关的对象");
//        redisMessageListenerContainer();
//    }
//
//    public RedisSentinelConfiguration redisSentinelConfiguration() {
//        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
//        // 设置一个主库
//        redisSentinelConfiguration.setMaster(new RedisNode("127.0.0.1", 6379));
//        // 下面还可以set多个从库
//        return redisSentinelConfiguration;
//    }
//
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jcf = new JedisConnectionFactory(redisSentinelConfiguration());
//        return jcf;
//    }
//
//    public StringRedisTemplate template() {
//        return new StringRedisTemplate(jedisConnectionFactory());
//    }
//
//    public KeyExpiresMessageListener keyExpiresMessageListener() {
//        KeyExpiresMessageListener keyExpiresMessageListener = new KeyExpiresMessageListener();
//        keyExpiresMessageListener.setRedisTemplate(template());
//        return keyExpiresMessageListener;
//    }
//
//    public RedisMessageListenerContainer redisMessageListenerContainer() {
//        RedisMessageListenerContainer topicContainer = new RedisMessageListenerContainer();
//        topicContainer.setConnectionFactory(jedisConnectionFactory());
//        ExecutorService executor = Executors.newFixedThreadPool(10);
//        topicContainer.setTaskExecutor(executor);
//        Set<Topic> topicSet = new HashSet<Topic>();
//        topicSet.add(new ChannelTopic("__keyevent@0__:expired"));
//        topicContainer.addMessageListener(keyExpiresMessageListener(), topicSet);
//        return topicContainer;
//    }
//
//
//
//}
