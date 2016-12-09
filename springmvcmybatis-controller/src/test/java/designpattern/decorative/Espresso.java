package designpattern.decorative;

/**
 * 浓缩咖啡
 * Created by Administrator on 2016/12/9 0009.
 */
public class Espresso extends Beverage{
    public Espresso(){
        description = "espresso";
    }

    public Espresso(int size){
        this();
        super.size = size;
    }

    public double cost() {
        return 8.8;
    }
}
