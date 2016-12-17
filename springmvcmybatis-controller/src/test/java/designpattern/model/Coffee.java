package designpattern.model;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public class Coffee extends CaffeineBeverage {
    void brew() {
        System.out.println("放点咖啡进去");
    }

    void addCondiments() {
        System.out.println("不加点糖,会很苦的...");
    }
}
