package algorithm;

import static algorithm.Selection.exchange;
import static algorithm.Selection.less;

/**
 * 插入排序
 * Created by Administrator on 2018/7/10 0010.
 */
public class Insertion {
    public static void main(String[] args) {
        int[] arr = new int[]{1,4,23,3,5467,5,9,10};
        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }


    }

    /**
     * 插入排序
     * @param arr
     */
    public static void sort(int[] arr){
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && arr[j]<arr[j-1]; j--) {
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }
    }

    public static void sort(Comparable[] arr){
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j-1]); j--) {
                exchange(arr, j, j - 1);
            }
        }
    }
}
