package algorithm;

/**
 * 归关排序算法
 * Created by Administrator on 2017/8/1 0001.
 */
public class GuiBinPaiXu {
    /*
        已有两个有序的数据,A,B,现在要求合并后有序C
     */
    public static void main(String[] args) {
        int[] arr1 = new int[]{23,36,99,100};
        int[] arr2 = new int[]{12,22,32,42,52,62,102,109,110};
        int[] arr3 = method1(arr1,arr2);
        for (int i = 0; i < arr3.length; i++) {
            System.out.println(arr3[i]);
        }


    }

    public static int[] method1(int[] arr1, int[] arr2){
        // 新建一个数组,存放合并后的数据
        int[] arr3 = new int[arr1.length + arr2.length];

        // 两个坐标,分别表示两个数据当前所在的位置.
        int index1 = 0, index2 = 0;
        for(int i =0;i<arr3.length;i++){
            // 某一个数据排序完后, 把另一个数组中的数据放入就可以了.
            if(index1>= arr1.length){
                arr3[i] = arr2[index2];
                index2++;
                continue;
            }
            if(index2>=arr2.length){
                arr3[i] = arr1[index1];
                index1++;
                continue;
            }
            if(arr1[index1] < arr2[index2]){
                arr3[i] = arr1[index1];
                index1++;
            }else{
                arr3[i] = arr2[index2];
                index2++;
            }
        }
        return arr3;
    }
}
