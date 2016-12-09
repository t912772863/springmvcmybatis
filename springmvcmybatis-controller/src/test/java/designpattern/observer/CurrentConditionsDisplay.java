package designpattern.observer;

/**
 * 当前状况布告板,
 * 此类实现了观察者接口,所以可以从主题那收到消息 .
 * 实现了displayelement接口.因为我们规定这是所有布告版要实现的接口
 * Created by Administrator on 2016/12/9 0009.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    /**
     * 构造器在主题用来注册自己成为一个观察者
     * @param weatherData
     */
    public CurrentConditionsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    /**
     * 显示方法,把当前的信息展示出来.
     */
    public void display() {
        System.out.println(this.toString());
    }

    /**
     * 当接收到改变通知后,我们把数据记录下来,并调用显示方法.
     * @param temp
     * @param humidity
     * @param pressure
     */
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    @Override
    public String toString() {
        return "CurrentConditionsDisplay{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", weatherData=" + weatherData +
                '}';
    }
}
