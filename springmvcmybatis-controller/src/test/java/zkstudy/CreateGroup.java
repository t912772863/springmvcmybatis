package zkstudy;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 示例通过java连接zookeeper, 创建一个新的分组
 * Created by tianxiong on 2019/2/26.
 */
public class CreateGroup implements Watcher {
    /** session过期时间*/
    private static final int SESSION_TIMEOUT = 5000;
    /** zk连接对象*/
    private ZooKeeper zk;
    /** 初始化资源时, 用来倒计数的*/
    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    /**
     * 连接zk
     * @param hosts ip:port
     * @throws IOException
     * @throws InterruptedException
     */
    public void connect(String hosts) throws IOException, InterruptedException {
        /*
        创建一个zk连接对象,同时把自己当成一个观察者, 资源初始化进行倒计数
         */
        zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
        // 只有当下面的process方法触发完成后,走到这一步,连接成功
        connectedSemaphore.await();
    }

    /**
     * 观察者异步触发的方法, 当触发的事件为成功连接zk时, 进行一次倒计数
     * @param event
     */
    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected){
            connectedSemaphore.countDown();
        }

    }

    /**
     * 创建一个节点
     * zk中所有的节点都必须以/开头
     * @param groupName
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void create(String groupName) throws KeeperException, InterruptedException {
        String path = "/"+groupName;
        // 创建一个节点, 节点名, 值为null, 节点的acl权限为任何人可见, 节点类型为持久化节点
        String createdPath = zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("created: "+createdPath);
    }

    public void close() throws InterruptedException {
        zk.close();
    }

    public static void main(String[] args) throws Exception{
        CreateGroup createGroup = new CreateGroup();
        createGroup.connect("118.126.115.206:2181");
        createGroup.create("testGroup");
        createGroup.close();
    }


}
