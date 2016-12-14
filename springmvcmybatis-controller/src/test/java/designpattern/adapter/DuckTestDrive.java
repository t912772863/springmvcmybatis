package designpattern.adapter;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class DuckTestDrive {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();

        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("the turkey says ...");
        turkey.gobble();
        turkey.fly();

        System.out.println("the duck says ...");
        testDuck(duck);

        System.out.println("the duckadapter says ...");
        testDuck(turkeyAdapter);

    }

    static void testDuck(Duck duck){
        duck.quack();
        duck.fly();
    }
}
