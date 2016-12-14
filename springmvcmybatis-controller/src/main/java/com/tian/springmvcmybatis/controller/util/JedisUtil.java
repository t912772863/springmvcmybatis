package com.tian.springmvcmybatis.controller.util;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;
/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class JedisUtil {
    private static volatile JedisPool jedisPool = null;

    /**
     * 获取jedisPool
     * @return
     */
    public static JedisPool getJedisPoolInstance(){
        if(null == jedisPool){
            synchronized (JedisUtil.class){
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
     * 获取资源
     *
     * @return
     * @throws JedisException
     */
    public static Jedis getResource() throws JedisException {
        Jedis jedis = null;
        try {
            if(jedisPool == null){
                getJedisPoolInstance();
            }

            jedis = jedisPool.getResource();
        } catch (JedisException e) {
            returnBrokenResource(jedis);
            throw e;
        }
        return jedis;
    }
    /**
     * 获取缓存
     *
     * @param key
     *            键
     * @return 值
     */
    public static String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                value = StringUtils.hasText(value) && !"nil".equalsIgnoreCase(value) ? value : null;
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取缓存
     *
     * @param key
     *            键
     * @return 值
     */
    public static Object getObject(String key) {
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = toObject(jedis.get(getBytesKey(key)));
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置缓存
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public static String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置缓存
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public static String setObject(String key, Object value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(getBytesKey(key), toBytes(value));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取List缓存
     *
     * @param key
     *            键
     * @return 值
     */
    public static List<String> getList(String key) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.lrange(key, 0, -1);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取List缓存
     *
     * @param key
     *            键
     * @return 值
     */
    public static List<Object> getObjectList(String key) {
        List<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                List<byte[]> list = jedis.lrange(getBytesKey(key), 0, -1);
                value = new ArrayList();
                for (byte[] bs : list) {
                    value.add(toObject(bs));
                }
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置List缓存
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public static long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.rpush(key, (String[]) value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置List缓存
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public static long setObjectList(String key, List<Object> value,int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            List<byte[]> list = new ArrayList<byte[]>();
            for (Object o : value) {
                list.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) list.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     *
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public static long listAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.rpush(key, value);
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     *
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public static long listObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            List<byte[]> list = new ArrayList<byte[]>();
            for (Object o : value) {
                list.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) list.toArray());
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取缓存
     *
     * @param key
     *            键
     * @return 值
     */
    public static Set<String> getSet(String key) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.smembers(key);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取缓存
     *
     * @param key
     *            键
     * @return 值
     */
    public static Set<Object> getObjectSet(String key) {
        Set<Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = new HashSet();
                Set<byte[]> set = jedis.smembers(getBytesKey(key));
                for (byte[] bs : set) {
                    value.add(toObject(bs));
                }
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置Set缓存
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public static long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.sadd(key, (String[]) value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置Set缓存
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public static long setObjectSet(String key, Set<Object> value,int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Set<byte[]> set = new HashSet<byte[]>();
            for (Object o : value) {
                set.add(toBytes(o));
            }
            result = jedis.sadd(getBytesKey(key), (byte[][]) set.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public static long setSetAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.sadd(key, value);
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     *
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public static long setSetObjectAdd(String key, Object... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            Set<byte[]> set = new HashSet<byte[]>();
            for (Object o : value) {
                set.add(toBytes(o));
            }
            result = jedis.rpush(getBytesKey(key), (byte[][]) set.toArray());
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 获取Map缓存
     *
     * @param key
     *            键
     * @return 值
     */
    public static Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                value = jedis.hgetAll(key);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 获取Map缓存
     *
     * @param key
     *            键
     * @return 值
     */
    public static Map<String, Object> getObjectMap(String key) {
        Map<String, Object> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                value = new HashMap();
                Map<byte[], byte[]> map = jedis.hgetAll(getBytesKey(key));
                for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
                    value.put(new String(e.getKey()),toObject(e.getValue()));
                }
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 设置Map缓存
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public static String setMap(String key, Map<String, String> value,int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.hmset(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 设置Map缓存
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param cacheSeconds
     *            超时时间，0为不超时
     * @return
     */
    public static String setObjectMap(String key, Map<String, Object> value,
                                      int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                jedis.del(key);
            }
            Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
            for (Map.Entry<String, Object> e : value.entrySet()) {
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>) map);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     *
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public static String mapPut(String key, Map<String, String> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hmset(key, value);
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     *
     * @param key
     *            键
     * @param value
     *            值
     * @return
     */
    public static String mapObjectPut(String key, Map<String, Object> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
            for (Map.Entry<String, Object> e : value.entrySet()) {
                map.put(getBytesKey(e.getKey()), toBytes(e.getValue()));
            }
            result = jedis.hmset(getBytesKey(key), (Map<byte[], byte[]>) map);
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     *
     * @param key
     *            键
     *            值
     * @return
     */
    public static long mapRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(key, mapKey);
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     *
     * @param key
     *            键
     *            值
     * @return
     */
    public static long mapObjectRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hdel(getBytesKey(key), getBytesKey(mapKey));
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     *
     * @param key
     *            键
     *            值
     * @return
     */
    public static boolean mapExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(key, mapKey);
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     *
     * @param key
     *            键
     *            值
     * @return
     */
    public static boolean mapObjectExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.hexists(getBytesKey(key), getBytesKey(mapKey));
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key
     *            键
     * @return
     */
    public static long del(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(key)) {
                result = jedis.del(key);
            } else {
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 删除缓存
     *
     * @param key
     *            键
     * @return
     */
    public static long delObject(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            if (jedis.exists(getBytesKey(key))) {
                result = jedis.del(getBytesKey(key));
            } else {
            }
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     *
     * @param key
     *            键
     * @return
     */
    public static boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(key);
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /**
     * 缓存是否存在
     *
     * @param key
     *            键
     * @return
     */
    public static boolean existsObject(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.exists(getBytesKey(key));
        } catch (Exception e) {
        } finally {
            returnResource(jedis);
        }
        return result;
    }



    /**
     * 归还资源
     *
     * @param jedis
     */
    public static void returnBrokenResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnBrokenResource(jedis);
        }
    }

    /**
     * 释放资源
     *
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 获取byte[]类型Key
     *
     * @return
     */
    public static byte[] getBytesKey(Object object) {
        if (object instanceof String) {
            return ((String) object).getBytes();
        } else {
            return ObjectUtil.serialize(object);
        }
    }

    /**
     * Object转换byte[]类型
     *
     * @return
     */
    public static byte[] toBytes(Object object) {
        return ObjectUtil.serialize(object);
    }


    /**
     * byte[]型转换Object
     * @return
     */
    public static Object toObject(byte[] bytes) {
        return ObjectUtil.unserialize(bytes);
    }
}
