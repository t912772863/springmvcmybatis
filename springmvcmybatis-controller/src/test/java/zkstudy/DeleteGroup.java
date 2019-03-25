package zkstudy;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * 删除一个节点, 要删除一个节点时, 首先要删除它下面所有的子节点.
 *
 * 删除一个节点时, 除了要传一个节点名, 还要传一个版本号, 只有两个都匹配时才能成功删除, 这是为了防止多节点并发修改的问题.
 * 如果要忽略版本号, 则版本号值传 -1 就可以了
 * Created by tianxiong on 2019/2/26.
 */
public class DeleteGroup extends ConnectionWatcher {
    public void delete(String groupName) throws KeeperException, InterruptedException {
        String path = "/"+groupName;
        try {
            List<String> children = zk.getChildren(path, false);
            for(String s: children){
                zk.delete(path+"/"+s, -1);
            }
            zk.delete(path, -1);
        } catch (KeeperException.NoNodeException e) {
            System.out.println("no member: "+groupName);
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        DeleteGroup deleteGroup = new DeleteGroup();
        deleteGroup.connection("10.90.1.234:2181");
        deleteGroup.delete("oms-lock/com.yks.oms.task.JoomOrderGrabTask_lock");
    }


}
