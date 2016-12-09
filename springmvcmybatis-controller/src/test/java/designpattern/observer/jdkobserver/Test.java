package designpattern.observer.jdkobserver;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionsDisplay = new CurrentConditionDisplay(weatherData);
        weatherData.setMeasurements(12,23,34);
        Thread.sleep(3000);
        weatherData.setMeasurements(22,23,34);
    }
}
