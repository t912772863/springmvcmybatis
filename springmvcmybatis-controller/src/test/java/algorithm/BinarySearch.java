package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 二分查找算法
 * Created by Administrator on 2018/7/5 0005.
 */
public class BinarySearch {
    public static void main(String[] args) {
        // 生成一个1000万的数组
        int[] arr = new int[100000000];
        for (int i = 0; i < 100000000; i++) {
            arr[i] = i + 1;
        }
        long s1 = System.currentTimeMillis();
        System.out.println(search2(99999999, arr));
        System.out.println("遍历算法用时: " + (System.currentTimeMillis() - s1));
        long s2 = System.currentTimeMillis();
        System.out.println(search(99999999, arr));
        System.out.println("二分查找算法用时: " + (System.currentTimeMillis() - s2));
    }

    /**
     * 二分查找算法, 返回要找的值在给定数组的下标.
     * 如果不存在返回-1
     *
     * @param value 要找的值
     * @param arr   一个升序的数组
     * @return
     */
    public static int search(int value, int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > value) {
                hi = mid - 1;
            } else if (arr[mid] < value) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 遍历查询的方法
     *
     * @param value
     * @param arr
     * @return
     */
    public static int search2(int value, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 将一个10进制的数字转换成2进制字符串
     *
     * @param value
     * @return
     */
    public static String getBinaryStr(int value) {
        String s = "";
        for(int n = value; n> 0; n /= 2){
            s = n%2 + s;
        }
        return s;
    }

    /**
     * 接收一个整数value,返回不大于log以2为底,N的对数.的最大值
     * @return
     */
    public static int lg(long value){
        if(value <0){
            throw new RuntimeException("参数有误");
        }
        if(value <2){
            return 0;
        }
        int tem = 1;
        for (int i = 1; i < 1000; i++) {
            tem = tem *2;
            if(tem== value){
                return i;
            }else if(tem > value){
                return i-1;
            }
        }
        return -1;
    }

    /**
     * 接收一个整形数组arr, 以及一个整数m, 返回一个大小为m的数组, 其中第i个元素的值为i在参数arr数组中出现的次数.
     * 如果arr中的值均在0到m-1间, 则返回的数据所有元素的和应该arr.length
     * @param arr
     * @param m
     * @return
     */
    public static int[] histogram(int[] arr, int m){
        int[] result = new int[m];
        // 遍历并计算每个元素出现的次数
        Map<String,Integer> map = new HashMap<String,Integer>();
        for (int i:arr) {
            Integer in = map.get(i+"");
            if(in == null){
                map.put(i+"", new Integer(1));
            }else {
                map.put(i+"", new Integer(in+1));
            }
        }
        // 遍历map, 把值放入数组对应的位置
        for(String s: map.keySet()){
            int temp = Integer.parseInt(s);
            if(temp<m){
                result[temp] = map.get(s);
            }
        }
        return result;
    }
}
