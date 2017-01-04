package designpattern.theduck2;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class DuckSimulator {
    public static void main(String[] args) {
        DuckSimulator simulator = new DuckSimulator();
        AbstractDuckFactory duckFactory = new CountintDuckFactory();
        simulator.simulate(duckFactory);
    }

    private void simulate(AbstractDuckFactory duckFactory) {
        Quackable mallardDuck = duckFactory.createMallardDuck();
        Quackable redHeadDuck = duckFactory.createRedHeadDuck();
        Quackable duckCall = duckFactory.createDuckCall();
        Quackable rubberDuc = duckFactory.createRubberDuck();
        //这个是鹅
        Quackable goose = new GooseAdapter(new Goose());

        // 一次性管理一群
        Flock flock = new Flock();
        flock.add(mallardDuck);
        flock.add(redHeadDuck);
        flock.add(duckCall);
        flock.add(rubberDuc);

        System.out.println("\n Duck Simulator");

        simulate(flock);

        //得到统计的一共叫的次数
        System.out.println("一共叫了"+QuackCounter.getNumberOfQuacks()+"次");
    }

    public void simulate(Quackable quackable){
        quackable.quack();
    }
}
