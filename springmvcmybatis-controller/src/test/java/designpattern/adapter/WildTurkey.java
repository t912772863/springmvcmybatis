package designpattern.adapter;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class WildTurkey implements Turkey {
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    public void fly() {
        System.out.println("I`m flying a short distance");
    }
}
