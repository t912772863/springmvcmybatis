package designpattern.observer;

/**
 * 主题接口
 * Created by Administrator on 2016/12/8 0008.
 */
public interface Subject {
    /**
     * 注册观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者
     */
    void notifyObservers();
}
