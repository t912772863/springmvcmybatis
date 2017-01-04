package designpattern.theduck2;

/**
 * 鹅适配器
 * Created by Administrator on 2017/1/3 0003.
 */
public class GooseAdapter implements Quackable{
    public void registerObserver(Observer observer) {

    }

    public void notityObserver() {

    }

    Goose goose;
    public GooseAdapter(Goose goose){
        this.goose = goose;
    }

    public void quack() {
        goose.honk();
    }
}
