package algorithm;

import static algorithm.Selection.exchange;
import static algorithm.Selection.less;

/**
 * 希尔排序
 * Created by Administrator on 2018/7/10 0010.
 */
public class Shell {
    public static void main(String[] args) {
        int[] arr = new int[]{1,4,23,3,5467,5,9,10};
        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    public static void sort(int[] arr){
        int length = arr.length;
        int h = 1;
        while (h< length/3){
            h = 3*h+1;

        }
        while (h >= 1){
            // 将数组变成h有序
            for (int i = h; i < length; i++) {
                //
                for (int j = i; j >= h && arr[j] < arr[j - h] ; j -= h) {
                    int temp = arr[j];
                    arr[j] = arr[j-h];
                    arr[j-h] = temp;
                }
            }
            h = h/3;
        }
    }

    public static void sort(Comparable[] arr){
        int length = arr.length;
        int h = 1;
        while (h< length/3){
            h = 3*h+1;
        }
        while (h >= 1){
            // 将数组变成h有序
            for (int i = h; i < length; i++) {
                //
                for (int j = i; j >= h && less(arr[j], arr[j - h]) ; j -= h) {
                    exchange(arr, j, j-h);
                }
            }
            h = h/3;
        }

    }

}
