package zkstudy;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * java操作zk提供的api时,同步和异步方法示例.
 *
 * 异常事件方法都没有返回值,而是通过传入对象的回调方法触发, 只会触发一次, 如果需要一直触发则, 每次触发后都需要再次注册
 * Created by tianxiong on 2019/2/26.
 */
public class TongYiBu extends ConnectionWatcher{

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        TongYiBu tongYiBu = new TongYiBu();
        tongYiBu.connection("118.126.115.206:2181");
        // 同步方法
        Stat stat = tongYiBu.zk.exists("/testGroup", false);
        System.out.println(stat);

        tongYiBu.zk.exists("/testGroup", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("enent: "+ event.toString());
            }
        }, new AsyncCallback.StatCallback(){

            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                System.out.println("rc:"+rc+" path: "+path+" ctx: "+ctx+" stat: "+stat);
            }
        }, null);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
