package zkstudy;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.List;

/**
 * 实现一个程序, 查看一个节点下面所有的子节点, 或者说查看一个下面的所有成员.
 * Created by tianxiong on 2019/2/26.
 */
public class ListGroup extends ConnectionWatcher{
    public void list(String groupName) throws KeeperException, InterruptedException {
        String path = "/"+groupName;
        try{
            List<String> children = zk.getChildren(path, false);
            if(children.isEmpty()){
                System.out.println("no member in group: "+ groupName);
                System.exit(1);
            }
            for(String s: children){
                System.out.println(s);
            }

        }catch (KeeperException.NoNodeException e){
            System.out.println("group do not exist: "+groupName);
            System.exit(1);
        }

    }

    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        ListGroup listGroup = new ListGroup();
        listGroup.connection("118.126.115.206:2181");

        listGroup.list("testGroup");
    }
}
