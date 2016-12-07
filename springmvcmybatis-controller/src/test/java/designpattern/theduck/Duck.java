package designpattern.theduck;

/**
 * 鸭子类
 * Created by Administrator on 2016/12/6 0006.
 */
public abstract class Duck {
    protected FlyBehaivor flyBehaivor;
    protected QuackBehaivor quackBehaivor;

    public void setFlyBehaivor(FlyBehaivor flyBehaivor) {
        this.flyBehaivor = flyBehaivor;
    }

    public void setQuackBehaivor(QuackBehaivor quackBehaivor) {
        this.quackBehaivor = quackBehaivor;
    }

    /**
     * 鸭子叫的方法
     */
    public void preQuack(){
        quackBehaivor.quack();
    }

    public void preFly(){
        flyBehaivor.fly();
    }

    /**
     * 鸭子游泳方法
     */
    public void swim(){
        System.out.println("我是一个鸭子,我当然会游泳啦");
    }

    /**
     * 鸭子的描述方法,每个长的不一样,自己去实现吧
     */
    public abstract void display();
}
