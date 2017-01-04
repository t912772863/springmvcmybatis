package designpattern.theduck2;

/**
 * Created by Administrator on 2017/1/3 0003.
 */
public interface QuackObservable {
    void registerObserver(Observer observer);
    void notityObserver();
}
