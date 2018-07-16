package algorithm;

/**
 * 选择排序
 * Created by Administrator on 2018/7/10 0010.
 */
public class Selection {
    public static void main(String[] args) {
        int[] arr = new int[]{1,4,23,3,5467,5,9,10};
        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    /**
     * 选择排序.
     * 每次选出一个最小的
     * @param arr
     */
    public static void sort(int[] arr){

        for (int i = 0; i < arr.length; i++) {
            int index = i;
            int value = arr[i];
            // 一次循环找出了最小值,以及坐标
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j] < value){
                    value = arr[j];
                    index = j;
                }
            }
            // 把找出来的最小值与当前坐标交换位置
            arr[index] = arr[i];
            arr[i] = value;
        }

    }


    public static void sort(Comparable[] arr){
        for (int i = 0; i < arr.length; i++) {
            int index = i;
            // 一次循环找出了最小值,以及坐标
            for (int j = i+1; j < arr.length; j++) {
                if(less(arr[j], arr[i])){
                    index = j;
                }
            }
            // 把找出来的最小值与当前坐标交换位置
            exchange(arr,index,i);
        }
    }

    /**
     * 比较两个元素大小
     * @param c1
     * @param c2
     * @return
     */
    public static boolean less(Comparable c1, Comparable c2){
        return c1.compareTo(c2) > 0;
    }

    /**
     * 交换数组中两个下标的值
     * @param arr
     * @param p
     * @param q
     */
    public static void exchange(Comparable[] arr, int p, int q){
        Comparable c = arr[p];
        arr[p] = arr[q];
        arr[q] = c;
    }
}
