package multithread.threadsynchronized;

/**面试题:
 *
 * 子线程循环10次, 主线程循环100次, 子线程再运行10次, 主线程再循环100次, 如此循环50次
 * Created by Administrator on 2017/4/27 0027.
 */
public class SyncDemo01 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                for(int i = 1;i<= 50; i++ ){
                    synchronized (SyncDemo01.class){
                        for(int j = 1;j<=10;j++){
                            System.out.println("子线程外循环第 "+i+"次, 内循环第 "+j+"次");
                        }
                    }

                }
            }
        }).start();

        // 守护线程也就是主线程
        for(int i = 1;i<= 50; i++ ){
            synchronized (SyncDemo01.class){
                for(int j = 1;j<=100;j++){
                    System.out.println("主线程外循环第 "+i+"次, 内循环第 "+j+"次");
                }
            }

        }
    }
}
