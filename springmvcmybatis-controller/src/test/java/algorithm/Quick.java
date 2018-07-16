package algorithm;

import static algorithm.Selection.exchange;
import static algorithm.Selection.less;

/**
 * 快速排序
 * Created by Administrator on 2018/7/10 0010.
 */
public class Quick {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{new Integer(12),new Integer(2),new Integer(24),new Integer(112),new Integer(4),new Integer(5),new Integer(78),new Integer(56),new Integer(62),new Integer(8)};
        sort(arr);
        for (int i = 0; i <arr.length ; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(Comparable[] a){

        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo){
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);

    }

    /**
     * 找到一个分界点元素, 该元素左边的值都小于该值, 右边的值都大于该值
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi+1;
        Comparable v = a[lo];
        while (true){
            // 扫描左边, 检查扫描是否结束, 并交换元素.
            while (less(a[++i], v)){
                if(i == hi){
                    break;
                }
            }
            while (less(v, a[--j])){
                if(j == lo){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }
}
