package designpattern.theduck;

/**
 * 诱饵鸭子
 * Created by Administrator on 2016/12/7 0007.
 */
public class BaitDuck extends Duck{
    public BaitDuck(){}
    public BaitDuck(QuackBehaivor quackBehaivor,FlyBehaivor flyBehaivor){
        this.quackBehaivor = quackBehaivor;
        this.flyBehaivor = flyBehaivor;
    }

    public void display() {
        System.out.println("我中一只诱饵鸭子,我会叫,会游泳,但是不会飞...");
    }
}
