package zkstudy;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.io.IOException;

/**
 * 配置文件值变化观察者
 * Created by tianxiong on 2019/2/27.
 */
public class ConfigWatcher implements Watcher{
    private ActiveKeyValueStore store;

    public ConfigWatcher(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connection(hosts);
    }

    public void displayConfig() throws KeeperException, InterruptedException {
        String value = store.read(ConfigUpdater.PATH, this);
        System.out.println("read "+ConfigUpdater.PATH+" : "+value);
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getType() == Event.EventType.NodeDataChanged){
            try{
                /*
                 由于观察者的触发是一次性的, 所以每次都要调用read方法,这样才能获取未来更新的数据.我们不能确保一定能够接收到更新通知,因为在
                 接收观察事件和下一次读取之间的窗口期内, znode可能改变了很多次. 但是client可能没有注册观察模式,所以client不会收到通知. 在配置服务中
                 这不是一个什么问题, 因为配置只关心配置的最新版本. 不过还是在关注一下这个问题, 对自己所使用的业务场景是否会有影响.

                 */
                displayConfig();
            }catch (InterruptedException e){
                System.out.println("interrupter, exiting.");
                Thread.currentThread().interrupt();
            }catch (KeeperException e){
                System.err.printf("keeper exception: ",e);
            }
        }


    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ConfigWatcher configWatcher = new ConfigWatcher("118.126.115.206:2181");
        configWatcher.displayConfig();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
