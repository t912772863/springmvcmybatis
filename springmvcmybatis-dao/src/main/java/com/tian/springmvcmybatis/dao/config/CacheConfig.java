package com.tian.springmvcmybatis.dao.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
        RedisCacheManager redisCacheManager = new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(connection), RedisCacheConfiguration.defaultCacheConfig());
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
//        RedisConnectionFactory redisConnectionFactory = new RedisCl
//
//    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
