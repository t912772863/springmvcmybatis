package designpattern.theduck;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class RedHeadDuck extends Duck {
    public RedHeadDuck(){}
    public RedHeadDuck(QuackBehaivor quackBehaivor,FlyBehaivor flyBehaivor){
        this.quackBehaivor = quackBehaivor;
        this.flyBehaivor = flyBehaivor;
    }

    public void display() {
        System.out.println("我的头是红色的");
    }

    public void fly() {
        System.out.println("我会飞");
    }

    public void quack() {
        System.out.println("嘎嘎嘎");
    }
}
