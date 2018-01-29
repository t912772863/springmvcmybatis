package temp;

import com.tian.common.util.ActivemqUtils;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class Demo002 {
    static class A {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "A{" +
                    "name='" + name + '\'' +
                    '}';
        }

        @Override
        public void finalize() throws Throwable {
            // 重写finamelize()方法, 该方法在对象被回收的时候可能会被调用,但不保证调用
            System.out.println(this.toString() + "对象回收啦");
        }
    }

    public static void main(String[] args) throws Exception {
        ActivemqUtils.Producer producer = ActivemqUtils.getQueueProducerInstance(
                "jd_dish_queue");
        producer.sendText("aaa");
    }


}
