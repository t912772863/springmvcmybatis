package multithread.threadsynchronized;

/**
 * 传统的线程同步问题
 * Created by Administrator on 2017/4/27 0027.
 */
public class TraditionalThreadSynchronized {
    /*
     两个线程同时调用一个类中的方法, 因为没有加同步锁, 会出现并发问题, 导致输出被中断.
     */
    public static void main(String[] args) {
        final Outputter outputter = new Outputter();

        // 线程一输出tianxiong
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputter.output("tianxiong");
                }

            }
        }).start();

        // 线程2输出chenhaiying
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Thread.sleep(9);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    outputter.output("chenhaiying");
                }
            }
        }).start();

    }

   static class Outputter{
        // 方法前面可以加一个方法锁, 其锁对象默认为this, 也就是调用该方法的对象
       // 如果是静态方法 , 则默认的锁对象,是类的字节码对象,Outputter.class
        public void output(String name){

            // 对下面这块代码加一个同步锁就可以防止关发问题
            // 需要注意的是同步锁中的对象不能是每个线程所独有的对象,应该找一个共有的, 这样大家用的同一个锁才能防止并发问题
            synchronized (TraditionalThreadSynchronized.class){
                for (int i = 0; i< name.length();i++){
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }

        }
    }
}
