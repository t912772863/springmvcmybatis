package designpattern.adapter;

/**
 * 火鸡适配器类,把一个火鸡包装成一个鸭子
 * Created by Administrator on 2016/12/14 0014.
 */
public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }

    public void quack() {
        this.turkey.gobble();
    }

    public void fly() {
        for (int i = 0; i < 5; i++) {
            this.turkey.fly();
        }
    }
}
