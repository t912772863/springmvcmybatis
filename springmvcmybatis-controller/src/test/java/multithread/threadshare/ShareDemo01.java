package multithread.threadshare;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程内共享变量
 * Created by Administrator on 2017/5/1 0001.
 */
public class ShareDemo01 {
    private static Map<Thread, Integer> map = new HashMap<Thread, Integer>();

    public static void main(String[] args) {
        for (int i = 0;i<2;i++){
            new Thread(new Runnable() {
                public void run() {
                    int value = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()+" has put value: "+value);
                    map.put(Thread.currentThread(), value);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }


    static class A {
        public void get(){
            int value = map.get(Thread.currentThread());
            System.out.println("class A , thread "+Thread.currentThread()+", value = "+value);
        }
    }

    static class B{
        public void get(){
            int value = map.get(Thread.currentThread());
            System.out.println("class B , thread "+Thread.currentThread()+", value = "+value);
        }
    }
}
