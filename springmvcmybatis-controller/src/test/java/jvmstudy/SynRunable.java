package jvmstudy;

/**
 * 通过JConsole工具检测线程死锁
 * Created by Administrator on 2018/2/5 0005.
 */
public class SynRunable {
    static class MyRun implements Runnable{
        int a,b;
        public MyRun(int a, int b){
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(this.a)){
                synchronized (Integer.valueOf(this.b)){
                    System.out.println(a+b);
                }
            }

        }
    }

    public static void main(String[] args) {
        for(int i = 0;i < 200; i++){
            new Thread(new MyRun(1,2)).start();
            new Thread(new MyRun(2,1)).start();
        }

    }

}
