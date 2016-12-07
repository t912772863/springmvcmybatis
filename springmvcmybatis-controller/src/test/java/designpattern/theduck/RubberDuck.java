package designpattern.theduck;

/**
 * 电子玩具鸭子
 * Created by Administrator on 2016/12/6 0006.
 */
public class RubberDuck extends Duck{
    public RubberDuck(){}
    public RubberDuck(QuackBehaivor quackBehaivor,FlyBehaivor flyBehaivor){
        this.quackBehaivor = quackBehaivor;
        this.flyBehaivor = flyBehaivor;
    }
    public void display() {
        System.out.println("我是一只塑料鸭子,我是彩色的");
    }
}
