package designpattern.observer.jdkobserver;

import java.util.Observable;

/**
 * 继承jdk自还的Observable
 *
 * 我们不再需要管理观察者了
 * Created by Administrator on 2016/12/9 0009.
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    /**
     * 构造方法也不再需要为了记住观察者而建立数据结构了
     */
    public WeatherData(){

    }

    public void measurementsChanged(){
        // 标识状态已改变
        setChanged();
        // 下面的通知方法,没有传数据,就表示我们所使用的方式,为观察者主动来拉数据,而不是等待推送.
        notifyObservers();
    }

    public void setMeasurements(float temperature,float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
