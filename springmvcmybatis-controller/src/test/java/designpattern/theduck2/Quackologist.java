package designpattern.theduck2;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class Quackologist implements Observer {
    public void update(QuackObservable duck) {
        System.out.println("现在正在叫的是: "+duck.toString());
    }
}
