package algorithm;

import static org.apache.ibatis.ognl.OgnlOps.less;

/**
 * 归并排序
 * Created by Administrator on 2018/7/10 0010.
 */
public class Merge {
    private static Comparable[] aux;

    public static void main(String[] args) {

    }

    public static void sort(Comparable[] arr){
        aux = new Comparable[arr.length];
         sort(arr, 0, arr.length-1);
    }

    /**
     * 用递归分治的思想, 实现归并算法
     * @param arr
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] arr, int lo, int hi){
        if(hi <= lo){
            return;
        }
        int mid = lo +(hi-lo)/2;
        sort(arr, lo, mid);
        sort(arr,mid, hi);
        merge(arr, lo, mid, hi);
    }

    public static void merge(Comparable[] arr, int lo, int mid, int hi){
        int i = lo;
        int j = mid+1;
        for (int k = lo; k <= hi ; k++) {
            // 数据复制
            aux[k] = arr[k];
        }
        for (int k = lo; k <= hi ; k++) {
            if(i > mid){
                arr[k] = aux[j++];
            }else if(j > hi){
                arr[k] = aux[i++];
            }else if(less(aux[j], aux[i])){
                arr[k] = aux[j++];
            }else {
                arr[k] = aux[i++];
            }
        }
    }

    /**
     * 两两归并, 四四归并, 直到整个数组有序.
     * @param a
     */
    public static void sort2(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz) {// sz子数组大小
            for (int lo = 0; lo < N-sz; lo+= sz+sz) { // lo 子数组索引
                merge(a, lo, lo+sz+1, Math.min(lo+sz+sz-1, N-1));
            }
        }

    }

}
