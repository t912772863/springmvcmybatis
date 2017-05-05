package multithread.multithreadshare;

/**
 * 两个窗口加票, 两个窗口减票, 共享同一份数据
 * Created by Administrator on 2017/5/1 0001.
 */
public class MultiThreadShareData3 {
    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        for (int i = 0; i < 2; i++) {
            new Thread(new IncRunnable(tickets)).start();
            new Thread(new DecRunnable(tickets)).start();
        }

    }

}

class Tickets{
    private int count =100;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 投入票, 票增加
     */
    public synchronized void inc(){
        count ++;
        System.out.println(Thread.currentThread().getName()+"加了一个票,现有"+count);
    }

    /**
     * 卖出票, 减少
     */
    public synchronized void dec(){
        count--;
        System.out.println(Thread.currentThread().getName()+"卖出一个票,现有"+count);
    }
}

class IncRunnable implements Runnable{
    private Tickets tickets;
    public IncRunnable(Tickets tickets){
        this.tickets = tickets;
    }
    public void run() {
        tickets.inc();
    }
}

class DecRunnable implements Runnable{
    private Tickets tickets;
    public DecRunnable(Tickets tickets){
        this.tickets = tickets;
    }
    public void run() {
        tickets.dec();
    }
}
