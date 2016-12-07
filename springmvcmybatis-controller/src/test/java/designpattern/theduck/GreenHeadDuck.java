package designpattern.theduck;

/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class GreenHeadDuck extends Duck{
    public GreenHeadDuck(){}
    public GreenHeadDuck(QuackBehaivor quackBehaivor,FlyBehaivor flyBehaivor){
        this.quackBehaivor = quackBehaivor;
        this.flyBehaivor = flyBehaivor;
    }

    public void display() {
        System.out.println("我的头是绿色的");
    }
}
