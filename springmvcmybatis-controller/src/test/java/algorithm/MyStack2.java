package algorithm;

import java.util.Iterator;

/**
 * 自己实现一个栈数据结构
 * 这个实现基于链表
 * Created by Administrator on 2018/7/6 0006.
 */
public class MyStack2<T> implements Iterable{
    /**
     * 总元素个数
     */
    private int n;
    /**
     * 用一个链表保存所有元素
     */
    private Node top;
//    private Node tope;

    public MyStack2(){

    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n ==0;
    }

    public void push(T t){
        Node node = new Node(t);
        node.setNextNode(top);
        top = node;
        n++;
    }

    public T pop(){
        T t = (T) top.getValue();
        top = top.getNextNode();
        n--;
        return t;
    }

    @Override
    public Iterator iterator() {
        return new MyStack2Itertor();
    }

    private class Node<T>{
        /**
         * 当前节点数据
         */
        private T value;
        /**
         * 下一个节点
         */
        private Node nextNode;
        public Node(T t){
            this.value = t;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", nextNode=" + nextNode +
                    '}';
        }
    }

    private class MyStack2Itertor implements Iterator<T>{
        private Node node = top;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T t = (T)node.getValue();
            node = node.getNextNode();
            return t;
        }
    }
}
