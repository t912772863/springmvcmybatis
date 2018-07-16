package algorithm;

/**
 * 冒泡摘译
 * Created by Administrator on 2018/7/10 0010.
 */
public class MaoPao {
    public static void main(String[] args) {
        int[] arr = new int[]{1,4,23,3,5467,5,9,10};
        sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }



    }

    /**
     * 冒泡排序算法, 从小到大
     * @param arr
     */
    public static void sort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] > arr[j]){
                    // 交换位置
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }

    }


}
