package zkstudy;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * zk分布式锁使用demo
 * Created by tianxiong on 2019/2/14.
 */
public class TestZkLock {
    public static void main(String[] args) throws Exception {
//        String zkServers = "192.168.201.211:2181,192.168.201.212:2181,192.168.201.213:2181";
        String zkServers = "127.0.0.1:2181";
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = new CuratorFrameworkImpl(CuratorFrameworkFactory.builder().retryPolicy(retry).connectString(zkServers)
                .sessionTimeoutMs(5000).connectionTimeoutMs(3000));
        client.start();

        for (int j = 0; j < 30; j++) {
            int finalJ = j;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <1000000 ; i++) {
                        InterProcessMutex lock2 = new InterProcessMutex(client, "/test-path"+i+ finalJ);
                        boolean b = false;
                        try {
                            b = lock2.acquire(0, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("/test-path"+i+ finalJ +":  "+ b);
                        try {
                            lock2.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


        }



    }
}
