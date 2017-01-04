package designpattern.theduck2;

/**
 * 此工厂创建没有被装饰过的鸭子
 * Created by Administrator on 2017/1/3 0003.
 */
public class DuckFactory extends AbstractDuckFactory {
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    public Quackable createRedHeadDuck() {
        return new ReadHeadDuck();
    }

    public Quackable createDuckCall() {
        return new DuckCall();
    }

    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
