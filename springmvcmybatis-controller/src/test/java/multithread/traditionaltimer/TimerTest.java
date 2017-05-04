package multithread.traditionaltimer;

import java.util.Timer;
import java.util.TimerTask;

/**传统的定时器运用
 * Created by Administrator on 2017/4/26 0026.
 */
public class TimerTest {
    public static void main(String[] args) {

        /*
        1. 创建一个定时器对象timer
        2. 创建一个定时任务timerTask
        3. timerTask对象中的run方法就是要执行的任务
        4. 调用定时器的schedule方法, 并传入任务对象, 设置10000为第一次启动的时候, 1000为以后每次间隔的时间
         */
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("boom!!!");
            }
        }, 10000,1000);


        /*
        扩展应用, 子母弹连环爆炸案
         */
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("父弹爆炸!!!");
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("子弹爆炸!!!");
                    }
                },2000);
            }
        },2000,8000);

        /*
        第一次调用的参数除了传入一个数值外, 还可以传入一个date对象, 指定第一次执行的准确时间, 后面再传入
        一个时间间隔, 就可以实现每天定时执行某一个任务
         */
    }
}
