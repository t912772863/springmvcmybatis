package algorithm;

/**
 * 一个固定大小 , 只能存放字符串的栈, 数据格式实现
 * Created by Administrator on 2018/7/5 0005.
 */
public class FixedCapacityStackOfStrings {
    /**
     * 内部用一个数组实现
     */
    private String[] a;
    /**
     * 标识数组所下标位置
     */
    private int N;
    public FixedCapacityStackOfStrings(int cap){
        a = new String[cap];
    }

    public boolean isEmpty(){
        return N ==0;
    }

    public int size(){
        return N;
    }

    public void push(String item){
        a[N++] = item;
    }

    public String pop(){
        return a[--N];
    }

}
