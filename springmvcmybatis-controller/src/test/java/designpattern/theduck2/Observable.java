package designpattern.theduck2;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public class Observable implements QuackObservable {
    ArrayList observers = new ArrayList();
    QuackObservable duck;

    public Observable(QuackObservable duck) {
        this.duck = duck;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notityObserver() {
        for(Object o : observers){
            ((Observer)o).update(duck);
        }

    }
}
