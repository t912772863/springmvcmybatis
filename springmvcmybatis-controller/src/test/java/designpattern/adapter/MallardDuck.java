package designpattern.adapter;

/**绿诚意鸭
 * Created by Administrator on 2016/12/14 0014.
 */
public class MallardDuck implements Duck {
    public void quack() {
        System.out.println("quack");
    }

    public void fly() {
        System.out.println("I`m flying");
    }
}
