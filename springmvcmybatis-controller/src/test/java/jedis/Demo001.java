package jedis;

import redis.clients.jedis.Jedis;

/**
 * Created by tian on 2016/10/17.
 */
public class Demo001 {

    public static void main(String[] args) {
        //连接本地的jedis服务
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //查看是否能连通,
        System.out.println("connection is OK: "+jedis.ping());

        int a = Integer.MAX_VALUE;
        int b = a+1;
        System.out.println(a);
        System.out.println(b);
    }
}
