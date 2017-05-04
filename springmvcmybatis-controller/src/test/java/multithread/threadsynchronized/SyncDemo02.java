package multithread.threadsynchronized;

/**
 * 优化方案:
 *
 * 子线程循环10次, 主线程循环100次, 子线程再运行10次, 主线程再循环100次, 如此循环50次
 * Created by Administrator on 2017/4/27 0027.
 */
public class SyncDemo02 {
    public static void main(String[] args) {
        final Business business = new Business();
        // 创建一个子线程执行任务
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1;i<=50 ; i++){
                    business.sub(i);
                }
            }
        }).start();

        // main线程, 也就是主线程也执行一个任务
        for(int i = 1;i<=50 ; i++){
            business.mai(i);
        }
    }

   static class Business{
        /** 当前是否轮到子线程执行*/
        private boolean isSub = true;
        /**
         * 主线程循环100 次的业务
         * @param i 外部循环次数
         */
        public synchronized void mai(int i){
            // 下面用while的时候, 被唤醒后还会再验证一次, 代码可靠性更高, 可以防止线程惊醒的情况
            while(isSub){
                // 如果进来以后发现不到主线程执行的时候, 则进行等待
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int j = 1;j<=100;j++){
                System.out.println("主线程外循环第 "+i+"次, 内循环第 "+j+"次");
            }
            // 主线程执行完了,改成轮到子线程
            isSub = true;
            // 同时唤醒一个其它线程, 这里除了当前线程, 只可能是子线程了
            this.notify();
        }

        /**
         * 子线程循环10次的业务
         * @param i 外部循环次数
         */
        public synchronized  void sub(int i){
            while(!isSub){
                // 如果进来以后发现不到子线程执行的时候, 则进行等待
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int j = 1;j<=10;j++){
                System.out.println("子线程外循环第 "+i+"次, 内循环第 "+j+"次");
            }
            // 子线程执行完一次, 改成轮到主线程
            isSub = false;
            // 同时唤醒一个其它线程, 这里除了当前线程, 只可能是主线程了
            this.notify();
        }
    }
}
