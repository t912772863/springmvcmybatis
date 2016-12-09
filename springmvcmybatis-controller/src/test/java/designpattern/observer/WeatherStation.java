package designpattern.observer;

/**
 * 气象站测试
 * Created by Administrator on 2016/12/9 0009.
 */
public class WeatherStation {
    public static void main(String[] args) throws InterruptedException {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        // 测试有数据变化时,看布告版的反应
        Thread.sleep(3000);
        weatherData.setMeasurements(80,76,60);
        Thread.sleep(3000);
        weatherData.setMeasurements(88,66,61);
        Thread.sleep(3000);
        weatherData.setMeasurements(82,79,62);

    }
}
