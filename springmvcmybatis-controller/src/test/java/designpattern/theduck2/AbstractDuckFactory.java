package designpattern.theduck2;

/**
 * 一个抽象鸭子工厂类,用来生产鸭子
 * Created by Administrator on 2017/1/3 0003.
 */
public abstract class AbstractDuckFactory {
    public abstract Quackable createMallardDuck();
    public abstract Quackable createRedHeadDuck();
    public abstract Quackable createDuckCall();
    public abstract Quackable createRubberDuck();
}
