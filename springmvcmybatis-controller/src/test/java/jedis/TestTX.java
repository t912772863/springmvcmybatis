package jedis;

import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * redis事务
 * Created by tian on 2016/10/18.
 */
public class TestTX {
    public static void main(String[] args) {
        // 连接到redis
//        Jedis jedis = new Jedis("127.0.0.1",6379);

        // 打开事务
//        Transaction transaction = jedis.multi();
//        transaction.set("k4","v4");
//        transaction.set("k5","v5");

        //提交,相当于commit
//        transaction.exec();
        // 放弃,相当于这个事务中的操作无效
//        transaction.discard();

        // watch方法,监控一个值,在提交事务前,如果发现这个值被修改了,那么放弃本次操作,拋出异常

        mehtod02();
    }

    /**
     * redis的事务
     */
    public static void mehtod02(){
        Jedis jedis = new Jedis("127.0.0.1",6379);

        //开始事务
        Transaction transaction = jedis.multi();

        transaction.set("k5","v5");
        transaction.set("k6","v6");
        transaction.set("k7","v7");

        //执行
        transaction.exec();
    }
}
