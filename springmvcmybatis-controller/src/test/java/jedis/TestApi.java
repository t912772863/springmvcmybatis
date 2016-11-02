package jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by tian on 2016/10/18.
 */
public class TestApi {
    public static void main(String[] args) {
        //连接本地的jedis服务
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //查看是否能连通,
        System.out.println("connection is OK: "+jedis.ping());

        jedis.set("k1","v1");
        jedis.set("k2","v2");
        jedis.set("k3","v3");

        //查询一个
        String s = jedis.get("k1");
        System.out.println(s);

        //查询所有
        Set<String> set = jedis.keys("*");

    }

}
