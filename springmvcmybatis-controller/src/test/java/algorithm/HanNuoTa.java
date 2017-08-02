package algorithm;

/**
 */
public class HanNuoTa {
    private static int temp=1;

    public static void main(String[] args) {
        /*
         * 汉诺塔问题
         *
         * A有三个大小不一的盘子, 要移动到C上, 现有中间柱子B.小的不能放大的上, 一次只能移动一个
         */
        mthod1(4,"A","B","C");

    }

    public static void mthod1(int topN, String from, String inner, String to){
        System.out.println(temp++);
        if(topN == 1){
            System.out.println("从"+from+"移动第"+topN+"个盘子到"+to+"上");
        }else{
            //topN-1 from 到 inner
            mthod1(topN -1,from,to,inner);
            System.out.println("从"+from+"移动第"+topN+"个盘子到"+to+"上");

            //topN-1 inner 到 to
            mthod1(topN-1,inner,from,to);
        }

    }


}
