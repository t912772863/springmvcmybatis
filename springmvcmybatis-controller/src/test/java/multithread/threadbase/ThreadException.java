package multithread.threadbase;

/**
 * 线程未检测异常的统一处理
 * Created by Administrator on 2017/3/8 0008.
 */
public class ThreadException {

    public static void main(String[] args) {
        A a = new A();
        Thread thread =  new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if(i==99){
                        throw new RuntimeException();
                    }
                }
            }
        });
        // 给线程添加一个未检测异常的处理器
        thread.setUncaughtExceptionHandler(a);
        thread.start();
    }

    static class A implements Thread.UncaughtExceptionHandler{

        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName()+"出现异常"+e.getMessage());
        }
    }

}
