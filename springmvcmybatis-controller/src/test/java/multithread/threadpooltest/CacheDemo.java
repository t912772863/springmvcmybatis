package multithread.threadpooltest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自己实现一个缓存类
 * Created by Administrator on 2017/5/4 0004.
 */
public class CacheDemo {
    private Map<String, Object> map = new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

    }

    /**
     * 获取获取
     * @param key
     * @return
     */
    public Object getData(String key){
        Object object = null;
        try{
            // 上读锁,保证读可以并发, 写不能
            readWriteLock.readLock().lock();
            object = map.get(key);
            if(object == null){
                // 如果发现缓存中没有值, 则要从数据库中取, 也就是读操作要换成写
                // 所以先把前面的读锁释放, 再上一个写锁
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    // 这里再判断一次是否为空, 是因为有可能多线程并发的时候,读转写锁, 只有一个转成功, 其它线程在上面转换
                    // 的那一步等待, 等到转成写锁的那个线程再释放写锁的时候, 处于一个没有锁的状态, 这个时间前面并发的线程
                    // 中没有转成写锁的那些就有机会再次进行写操作了, 而其实这时候第一个线程已经写过了.
                    if(object == null){
                        object = "aaaa";
                    }
                }finally {
                    // 保证写完成后, 释放写锁
                    readWriteLock.writeLock().unlock();
                }
                // 正常写入值后, 又换成了读的流程, 所以重新加一个读锁
                readWriteLock.readLock().lock();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 读完后释放读锁
            readWriteLock.readLock().unlock();
        }
        return object;
    }
}
