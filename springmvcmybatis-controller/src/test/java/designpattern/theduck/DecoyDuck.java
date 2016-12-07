package designpattern.theduck;

/**
 * 模型鸭子,不会叫,不会飞的
 * Created by Administrator on 2016/12/6 0006.
 */
public class DecoyDuck extends Duck {
    public DecoyDuck(){}
    public DecoyDuck(QuackBehaivor quackBehaivor,FlyBehaivor flyBehaivor){
        this.quackBehaivor = quackBehaivor;
        this.flyBehaivor = flyBehaivor;
    }

    public void display() {
        System.out.println("我是一个模型鸭子,不会叫也不会飞,只能在手上漂着");
    }
}
