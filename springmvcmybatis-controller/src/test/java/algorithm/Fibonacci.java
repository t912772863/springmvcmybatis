package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契数列 F(n) = F(n-1) + F(n-2)
 * Created by Administrator on 2018/7/5 0005.
 */
public class Fibonacci {
    /**
     * 用于缓存已经运算过的数列结果
     */
    private static Map<Long, Long> map = new HashMap<Long , Long>();
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println("F2("+i+") = "+F2(i));
        }
    }

    /**
     * 实现1
     * @param n
     * @return
     */
    public static long F(long n){
        if(n == 0){
            return 0;
        }else if(n==1){
            return 1;
        }
        return F(n-1)+F(n-2);
    }

    /**
     * 上面第一个版本到后面运行越来越慢, 因为递归层次太深了, 返回很慢.
     *  通过观察, 发现有大量的运算是重复的, 所以这里进行一个优化, 把前面算过的值进行缓存,
     *  缓存没有的数据才进行运算
     * @param n
     * @return
     */
    public static long F2(long n){
        // 先从缓存中获取看是否已经算过了,
        Long temp = map.get(new Long(n));
        if(temp != null){
            return temp;
        }
        if(n == 0){

            return 0;
        }else if(n==1){
            return 1;
        }
        long result = F2(n-1)+F2(n-2);
        map.put(new Long(n), result);
        return result;
    }
}
