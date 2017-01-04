package designpattern.theduck2;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class Flock implements Quackable {
    ArrayList<Quackable> quackables = new ArrayList<Quackable>();

    public void add(Quackable quackable){
        quackables.add(quackable);
    }

    public void registerObserver(Observer observer) {

    }

    public void notityObserver() {

    }

    public void quack() {
        for(Quackable q : quackables){
            q.quack();
        }
    }
}
