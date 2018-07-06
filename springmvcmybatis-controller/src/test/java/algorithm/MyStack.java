package algorithm;

import java.util.Iterator;

/**
 * 自己实现一个线程非安全的stack数据结构
 * 这个实现内部是基于数组的.
 * Created by Administrator on 2018/7/5 0005.
 */
public class MyStack<T> implements Iterable<T> {
    private T[] a;
    private int N;
    public MyStack(){
        a = (T[])new Object[10];
    }
    public boolean isEmpty(){
        return N ==0;
    }

    public int size(){
        return N;
    }

    public void push(T item){
        // 添加的时候先检查数组是否需要扩容
        if(N == a.length){
            reSize(a.length * 2);
        }
        a[N++] = item;
    }

    public T pop(){
        T t = a[--N];
        // 弹出时, 如果数组太空, 则缩容
        if(N <= a.length/4){
            reSize(a.length/2);
        }
        // 弹出后把对应位置设置为null, 防止对象游离
        a[N] = null;
        return t;
    }

    private void reSize(int max){
        T[] newArr = (T[])new Object[max];
        for (int i = 0; i < N; i++) {
            newArr[i] = a[i];
        }
        a = newArr;
    }

    public boolean hasNext() {
        return N >0;
    }

    public T next() {
        return a[--N];
    }

    public Iterator<T> iterator(){
        return new FixedCapcityStackIterator();
    }

    private class FixedCapcityStackIterator implements Iterator<T>{
        private int n = N;

        @Override
        public boolean hasNext() {
            return n>0;
        }

        @Override
        public T next() {
            return a[--n];
        }
    }
}
