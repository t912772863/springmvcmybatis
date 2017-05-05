package multithread.multithreadshare;

/**
 * 如果每个线程执行的代码相同, 可以使用同一个Runnable对象, 这个对象中有那个共享数据,就可以实现线程间的共享数据问题
 * 如: 常见的卖票系统.
 * Created by Administrator on 2017/5/1 0001.
 */
public class MultiThreadShareData2 {
    public static void main(String[] args) {
        // 定义好票库
        Ticket ticket = new Ticket();

        // 三个窗口一起卖
        for (int i=0;i< 3;i++){
            //每次卖一个总票数减一
            new Thread(ticket).start();
        }


    }
}

class Ticket implements Runnable{
    private int count = 100;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public synchronized void run() {
        count--;
        System.out.println("窗口:"+Thread.currentThread().getName()+"卖了一张票");
        System.out.println("当前剩余票数为: "+count);
    }

}
