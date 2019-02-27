package zkstudy;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.io.IOException;

/**
 * 住一个已经有组中添加一个成员, 其实就是在一个节点下面创建子节点
 * Created by tianxiong on 2019/2/26.
 */
public class JoinGroup extends ConnectionWatcher {
    public void join(String groupName,String memberName) throws KeeperException, InterruptedException {
        String path = "/"+groupName+"/"+memberName;
        String createdPath = zk.create(path,null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        JoinGroup joinGroup = new JoinGroup();
        joinGroup.connection("118.126.115.206:2181");
        joinGroup.join("testGroup", "member1");
        // 在下面这行休眠结束前可以通过zk的客户端命令get /testGroup/member1 查看到节点信息, 当下面时间到了,节点自动删除
        Thread.sleep(100000);
    }

}
