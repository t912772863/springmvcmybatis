package temp;

import io.goeasy.GoEasy;

/**
 * GoEasy是一个第三服务,通过注册一个用户可以实现后台住页面推送消息,页面间消息传送,其它移动设备推送消息到页面等功能
 * Created by Administrator on 2017/2/7 0007.
 */
public class TestGoEasy {
    public static void main(String[] args) {
        GoEasy goEasy = new GoEasy("256648b7-adc4-4235-b994-3c543898ad54");
        goEasy.publish("channel_all", "Hello world!");
    }

}
