package multithread.threadbase;

/**
 * 线程入门
 * Created by Administrator on 2017/4/26 0026.
 */
public class ThreadDemo01 {
    public static void main(String[] args) {
        // 创建一个线程, 方法一
        Thread thread = new Thread(){
            @Override
            public void run(){
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 获取到当前的线程, 再获取线程的名字
                    System.out.println("1: "+Thread.currentThread().getName());
                    //
                    System.out.println("2: "+this.getName());
                }
            }

        };
        // 启动线程, 会调用线程中的run方法
        thread.start();


        // 创建一个线程, 方法二, 这种方法更常用, 更体现了面向对象的思想

        /*
         线程在调用run方法后, 会查看线程内部的target对象是否为空, 如果不为空,则会调用target的run方法, target对象是一个Runnable对象
         */
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("3: "+Thread.currentThread().getName());
                }

            }
        });

        thread2.start();

        /*
         测试题
         下面创建了一个线程的子类, 同时给线程一个runnable任务, 那么调用start方法用, 会输出什么呢?

         会输出子为的打印:
         首先子类重写了Thread父类的run方法, 那么在调用线程的启动方法start后, 会调用线程对象也就是我们创建的
         线程子类的run方法, 如果我们没有重写该方法才会去调用父类的run方法, 而正如前面所提到的那样, 调用target对象中的
         run方法的逻辑在存在于父类中的run方法中的, 而在我们重写的子类中的run方法并没有这样的逻辑, 也就是在创建该对象时
         构造方法中所传入的Runnable对象没有任何的意义.
          */
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("runnable: "+Thread.currentThread().getName());
                }
            }
        }){
            public void run(){
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread: "+Thread.currentThread().getName());
                }

            }
        }.start();
    }
}
