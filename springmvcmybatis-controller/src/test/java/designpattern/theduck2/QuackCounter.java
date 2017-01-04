package designpattern.theduck2;

/**
 * 一个装饰者,在不改变原来形为的同时,加了一个功能,记录鸭子叫的次数
 * Created by Administrator on 2017/1/3 0003.
 */
public class QuackCounter implements Quackable {
    public void registerObserver(Observer observer) {

    }

    public void notityObserver() {

    }

    Quackable duck;
    static int numberOfQuacks;
    public QuackCounter (Quackable quackable){
        this.duck = quackable;
    }

    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }

    public static int getNumberOfQuacks(){
        return numberOfQuacks;
    }
}
