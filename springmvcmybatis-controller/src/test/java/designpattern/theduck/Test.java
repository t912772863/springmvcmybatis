package designpattern.theduck;

/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class Test {
    public static void main(String[] args) {
        Duck duck = new BaitDuck(new Squack(),new FlyNoWay());
        duck.preQuack();
        duck.preFly();
        duck.display();
        duck.swim();
    }

}
