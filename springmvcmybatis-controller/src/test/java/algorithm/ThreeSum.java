package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 给定一组整数, 从中取出三个数, 和等于0, 看有多少种组合.假设元素不重复
 * 要求一个数字不可以用多次
 * Created by Administrator on 2018/7/6 0006.
 */
public class ThreeSum {
    public static void main(String[] args) {
        // 生成数组
        int length = 4000;
        Random random = new Random();
        // 保存已经有的值,防止重复
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            int a = random.nextInt(length*2);
            set.add(new Integer(a -length));
        }
        Object [] arr =  set.toArray();
        int[] arr2 = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[i] = (Integer) arr[i];
        }

        // 测试结果
        long s = System.currentTimeMillis();

//        System.out.println(get(arr2) +"use time: "+(System.currentTimeMillis() -s));
        long s2 = System.currentTimeMillis();
        System.out.println(get2(arr2)+"use time: "+(System.currentTimeMillis() -s2));
    }

    /**
     * 方法一, 三层循环, 时间复杂度为N的3次方
     * @param arr
     * @return
     */
    public static int get(int[] arr){
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                for (int k = j+1; k < arr.length; k++) {
                    int sum = arr[i] + arr[j] +arr[k];
                    if(sum == 0){
                        temp++;
                        System.out.println("["+arr[i]+","+arr[j]+","+arr[k]+"]");
                    }
                }
            }
        }
        return temp;
    }

    /**
     * 方法2,先排序, 再用二分查找
     * @param arr
     * @return
     */
    public static int get2(int[] arr){
        int temp =0;
        // 先排序, 从小到大
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                // 因为现在数组是有序的了, 所以最里层用2分查找. 因为这个时候前面两个数的和是确定的
                // 所以可以快速用二分查找, 找相反数
                int twoSum = arr[i]+arr[j];
                int index = BinarySearch.search2(0-twoSum, arr);
                if(index > j){
                    // 防止数据用多次, 且!=-1表示有查到
                    temp ++;
                }
            }
        }
        return temp;
    }

}
