package jvmstudy;

/**
 * -Xms20M 虚拟机最小内存 20M
 * -Xmx20M 虚拟机最大内存 20M
 * -Xmn10M 新生代内存 10M
 * -XX:SurvivorRatio=8 新生代中Eden和Survivor的比例为8:1
 * -XX:+PrintGCDetails 打印GC的详细日志
 * Created by Administrator on 2018/2/2 0002.
 */
public class TestGC01 {
    private static final int _1MB = 1024*1024;

    /**
     * VM参数: -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     *
     */
    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB * 2];
        allocation2 = new byte[_1MB * 2];
        allocation3 = new byte[_1MB * 2];
        allocation4 = new byte[_1MB * 4];

    }

    public static void main(String[] args) {
        TestGC01.testAllocation();
    }

}
