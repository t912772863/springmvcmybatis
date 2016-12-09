package designpattern.decorative;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class Test {
    public static void main(String[] args) {
        Beverage beverage2 = new HouseBlend(3);
        System.out.println(beverage2.getDescription()+"   "+ beverage2.cost());
        beverage2 = new Mocha(beverage2);
        System.out.println(beverage2.getDescription()+"   "+ beverage2.cost());


    }
}
