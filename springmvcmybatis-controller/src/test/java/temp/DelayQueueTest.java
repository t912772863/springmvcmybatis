package temp;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 演示延时队列的基本用法
 * Created by Administrator on 2018/3/8 0008.
 */
public class DelayQueueTest {
    /*
    DelayQueue的元素必须实现Delayed接口, 我们可以参考ScheduledThreadPoolExecutor里ScheduledFutureTask类的实现, 一共
    有三步.
    第一步: 在对象创建的时候, 初始化基本数据. 使用time记录当前对象延时到什么时候可以使用. 使用sequenceNumber来标识
    元素在队列中的先后顺序.
    第二步: 实现getDelay方法, 该方法返回当前元素还有多长时间可以使用, 单位是纳秒.
    第三步: 实现compareTo方法, 对元素进行排序
     */

    public static void main(String[] args) {
        DelayQueue<MyDelayObject> delayQueue = new DelayQueue();
        delayQueue.put(new MyDelayObject(new Date(System.currentTimeMillis() + 1000*3), "我是对象一"));
        delayQueue.put(new MyDelayObject(new Date(System.currentTimeMillis() + 1000*9), "我是对象二"));
        delayQueue.put(new MyDelayObject(new Date(System.currentTimeMillis() + 1000*1), "我是对象三"));

        try {
            while (!delayQueue.isEmpty()){
                MyDelayObject o  = delayQueue.take();
                System.out.println(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static class MyDelayObject implements Delayed {
        /**
         * 对象的到期时间
         */
        Date useTime;
        String name;

        public MyDelayObject(Date time, String name){
            this.useTime = time;
            this.name = name;
        }

        /**
         * 返回对象还有多长时间可以使用, 单位纳秒
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(useTime.getTime() - System.currentTimeMillis(), TimeUnit.NANOSECONDS) ;
        }

        @Override
        public int compareTo(Delayed o) {
            if(o instanceof MyDelayObject){
                MyDelayObject other = (MyDelayObject)o;
                long temp = this.useTime.getTime() - other.useTime.getTime();
                return temp > 0 ? 1 : temp == 0 ? 0 : -1;
            }else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "MyDelayObject{" +
                    "useTime=" + useTime +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
