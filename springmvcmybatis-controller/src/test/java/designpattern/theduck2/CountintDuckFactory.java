package designpattern.theduck2;

/**
 * 此工厂创建装饰过的鸭子
 * Created by Administrator on 2017/1/3 0003.
 */
public class CountintDuckFactory extends AbstractDuckFactory {
    public Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }

    public Quackable createRedHeadDuck() {
        return new QuackCounter(new ReadHeadDuck());
    }

    public Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }

    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
