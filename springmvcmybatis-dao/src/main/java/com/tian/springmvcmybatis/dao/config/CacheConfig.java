package com.tian.springmvcmybatis.dao.config;

import com.tian.springmvcmybatis.dao.common.RedisCacheErrorHandler;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.AbstractCacheInvoker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

/**
 * service层和Controller层可以不关注数据持久层的缓存,所以该配置文件放在dao层
 * 通过java的方式注入Bean,该配置文件主要配置缓存相关
 * Created by Administrator on 2017/7/11 0011.
 */
// 标识该类为一个配置类
@Configuration
// 启用缓存
@EnableCaching
public class CacheConfig{
    /**
     * 声明缓存处理器
     * @return
     */
    @Bean
    public CacheManager cacheManager( RedisConnectionFactory connection){
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        // 设置有效期
        configuration = configuration.entryTtl(Duration.ofDays(1));

        RedisCacheManager redisCacheManager = new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connection), configuration);
        return redisCacheManager;
    }

    /**
     * 单机版本redis注入
     * @return
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory(){
        // 这里是单机模式的redis
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    /**
     * 哨兵版本redis注入.
     * @return
     */
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(){
//        // 这里是哨兵模式的redis
//        RedisSentinelConfiguration sentinel = new RedisSentinelConfiguration();
//        RedisNode redisNode = new RedisNode("118.126.115.206",6379);
//        redisNode.setName("host6379");
//        sentinel.setMaster(redisNode);
//        List<RedisNode> list = new ArrayList<RedisNode>();
//        // 哨兵的地址
//        list.add(new RedisNode("118.126.115.206", 26379));
//        sentinel.setSentinels(list);
//        RedisConnectionFactory redisConnectionFactory =new JedisConnectionFactory(sentinel,null);
//        return redisConnectionFactory;
//    }

    /**
     * 集群版本redis
     * @return
     */
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(){
//        RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration();
//        /*
//        集群模式下, 多个节点, 其实只要连接一下节点就可以正常使用了, 不过如果该节点挂了, 就不能正常使用, 所以一般把集群的多个节点都配置上
//         */
//        RedisNode redisNode1 = new RedisNode("10.90.1.179",7001);
//        RedisNode redisNode2 = new RedisNode("10.90.1.179",7002);
//        RedisNode redisNode3 = new RedisNode("10.90.1.179",7000);
//        RedisNode redisNode4 = new RedisNode("10.90.1.180",7003);
//        RedisNode redisNode5 = new RedisNode("10.90.1.180",7004);
//        RedisNode redisNode6 = new RedisNode("10.90.1.180",7005);
//        clusterConfig.addClusterNode(redisNode1);
//        clusterConfig.addClusterNode(redisNode2);
//        clusterConfig.addClusterNode(redisNode3);
//        clusterConfig.addClusterNode(redisNode4);
//        clusterConfig.addClusterNode(redisNode5);
//        clusterConfig.addClusterNode(redisNode6);
//
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(clusterConfig);
//        return jedisConnectionFactory;
//    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 下面配置这两个bean配置了spring cache缓存默认的redis缓存异常, 导致服务不可用问题.
     * 不过要注意的一点是, 如果要这样用缓存, redis最好设置成不做持久化, 这样可以避免数据
     * 的不一致性问题.
     * @return
     */
    @Bean
    public RedisCacheErrorHandler redisCacheErrorHandler(){
        return new RedisCacheErrorHandler();
    }

    @Bean
    public AbstractCacheInvoker abstractCacheInvoker(AbstractCacheInvoker abstractCacheInvoker, RedisCacheErrorHandler redisCacheErrorHandler){
        abstractCacheInvoker.setErrorHandler(redisCacheErrorHandler);
        return abstractCacheInvoker;
    }
}
