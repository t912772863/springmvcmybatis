package designpattern.observer;

/**
 * 观察者接口
 * Created by Administrator on 2016/12/8 0008.
 */
public interface Observer {
    /**
     * 所有观察者都要实现该接口,当主题变动时调用这个方法,把相关参数传给观察者
     * @param temp
     * @param humidity
     * @param pressure
     */
    void update(float temp, float humidity, float pressure);
}
