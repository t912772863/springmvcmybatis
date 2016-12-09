package designpattern.observer.jdkobserver;

import designpattern.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * 当前状况布告牌
 * Created by Administrator on 2016/12/9 0009.
 */
public class CurrentConditionDisplay implements Observer,DisplayElement{
    Observable observable;
    private float tempreture;
    private float humidity;

    /**
     * 构造方法需要一个Observable当作参数,用来把当前对象记为观察者
     * @param observable
     */
    public CurrentConditionDisplay(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    public void display() {
        System.out.println(this.toString());
    }

    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
            this.tempreture = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }

    }

    @Override
    public String toString() {
        return "CurrentConditionDisplay{" +
                "observable=" + observable +
                ", tempreture=" + tempreture +
                ", humidity=" + humidity +
                '}';
    }
}
