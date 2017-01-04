package designpattern.theduck2;



/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class MallardDuck implements Quackable {
    Observable observable;
    public MallardDuck() {
        observable = new Observable(this);
    }
    public void registerObserver(Observer observer) {
        observable.registerObserver(observer);
    }

    public void notityObserver() {
        observable.notityObserver();
    }

    public void quack() {
        System.out.println("Quack");
        notityObserver();
    }
}
