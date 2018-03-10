package jvmstudy;

import java.util.ArrayList;
import java.util.List;

/**
 * java虚拟机, 堆溢出演示
 *
 * -Xms20m -Xmx20m 分别表示设置jvm内存的最小值和最大值. 当这两个值设置相同时, 则虚拟机不会自动调整大小
 * 堆内存不足时: java.lang.OutOfMemoryError: Java heap space
 * Created by Administrator on 2018/2/1 0001.
 */
public class HeapOOM {
    static int i = 10;
    static {
        i=0;
        System.out.println(i);
    }


    private byte[] bytes = new byte[1000000];

    public static void main(String[] args) {
        List<HeapOOM> list = new ArrayList<HeapOOM>();
        int i = 0;
        while (true){
            list.add(new HeapOOM());
            System.out.println(i++);
        }

    }
}
