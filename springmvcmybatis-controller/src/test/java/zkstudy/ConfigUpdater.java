package zkstudy;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;

/**
 * 配置信息更新者
 * Created by tianxiong on 2019/2/27.
 */
public class ConfigUpdater {
    public static final String PATH = "/config";
    private ActiveKeyValueStore store;
    private Random random = new Random();

    public ConfigUpdater(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connection(hosts);
    }

    public void run() throws KeeperException, InterruptedException {
        while (true){
            String value = random.nextInt(100)+"";
            store.write(PATH, value);
            System.out.println("set "+PATH+" = "+value);
            Thread.sleep(random.nextInt(1000));
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        while (true){
            try{
                ConfigUpdater configUpdater = new ConfigUpdater("118.126.115.206:2181");
                configUpdater.run();
            }catch (KeeperException.SessionExpiredException e){
                /* session过期了,则需要重新建立一个连接
                   这里还有另一个情况,比如集群不可用了, 可以有多种策略, 比如一直重试, 直到集群恢复为止,
                   也可以对重连的间隔时间加一个变长时间间隔等.
                 */
                ConfigUpdater configUpdater = new ConfigUpdater("118.126.115.206:2181");
                configUpdater.run();
            }catch (Exception e){
                // 未知异常, 打印出来.
                e.printStackTrace();
                break;
            }

        }

    }

}
