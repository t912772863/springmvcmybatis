package zkstudy;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;

/**
 * 一个简单版本的配置服务, 当配置内容变化时, 可以通知客户端
 * Created by tianxiong on 2019/2/27.
 */
public class ActiveKeyValueStore extends ConnectionWatcher {
    /**
     * 需要注意的一点zookeeper对节点的值, 只支持utf-8编码, 其它类型的编码,可能导致异常
     */
    private static final Charset CHARSET = Charset.forName("UTF-8");
    /** 最多尝试次数*/
    private static final int MAX_RETRIES = 3;
    /** 每次操作失败后, 重试前休眠时间*/
    private static final int RETRY_PER_SECOND = 1;

    public void write(String path, String value) throws KeeperException, InterruptedException {
        /*
        由于zk通用用于分布式服务中, 由于zk中节点本身的版本机制, 以及一致性, 高可用性等. 所以并不能保证每次写都能成功.
        这里设置一个重试机制, 当一次写失败后会再重试,达到最大次数.
         */
        int retries = 0;
        while (true){
            try{
                Stat stat = zk.exists(path, false);
                if(stat == null){
                    // 如果节点不存在, 则先创建这个节点, 并设置值
                    zk.create(path, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT );
                }else {
                    // 节点已经存在,则直接设置值(忽略版本号)
                    zk.setData(path, value.getBytes(CHARSET), -1);
                }
                return;
            }catch (KeeperException.SessionExpiredException e){
                // 当拋出的是session过期异常时, 不进行重试, 因为已经无法重连了,重试没有意义
                throw e;
            }catch (KeeperException e){
                if(retries++ > MAX_RETRIES){
                    throw e;
                }
                Thread.sleep(RETRY_PER_SECOND*1000);
            }


        }

    }

    /**
     * 获取一个点的值
     * @param path 节点名
     * @param watcher 观察者
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String read(String path, Watcher watcher) throws KeeperException, InterruptedException {
        byte[] data = zk.getData(path,watcher, null);
        return new String(data, CHARSET);
    }


}
