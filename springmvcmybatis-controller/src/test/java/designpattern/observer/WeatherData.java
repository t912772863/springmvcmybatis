package designpattern.observer;

import java.util.ArrayList;

/**
 * 气象台
 * Created by Administrator on 2016/12/8 0008.
 */
public class WeatherData implements Subject {
    /** 存放观察者*/
    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers = new ArrayList<Observer>();
    }

    /**
     * 注册观察者
     */
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * 移除观察者
     * @param observer
     */
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if(i>= 0){
            observers.remove(i);
        }
    }

    /**
     * 通知所有观察者
     */
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(temperature,humidity,pressure);
        }

    }

    /**
     * 当从气象台得到更新的数据时,我们通知所有的观察者
     */
    public void measurementsChanged(){
        notifyObservers();
    }

    /**
     * 模拟气象站数据变化
     */
    public void setMeasurements(float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
