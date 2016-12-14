package jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by tian on 2016/10/26.
 */
public class JedisPoolUtil {
    private static volatile JedisPool jedisPool = null;

    private JedisPoolUtil(){

    }

    public static JedisPool getJedisPoolInstance(){
        if(null == jedisPool){
            synchronized (JedisPoolUtil.class){
                if(null == jedisPool){
                    //jedis连接池的一些配置
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    //最多可以实例化的连接

                    //当可用连接只剩下32个的时候,会提醒
                    jedisPoolConfig.setMaxIdle(32);
                    //最长等待时间
                    jedisPoolConfig.setMaxWaitMillis(100*1000);
                    // 从连接池中获取一个连接的时候,检测这个连接的可用性,即ping,pong
                    jedisPoolConfig.setTestOnBorrow(true);

                    jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379);
                }
            }
        }
        return jedisPool;
    }

    /**
     * 还对象
     * @param jedisPool
     * @param jedis
     */
    public static void release(JedisPool jedisPool, Jedis jedis){
        if(null != jedis){
            jedisPool.returnBrokenResource(jedis);
        }
    }

    public static void main(String[] args) {
        JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();

        Jedis jedis = jedisPool.getResource();
//        jedis.

    }

}
