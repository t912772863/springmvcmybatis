package designpattern.decorative;

/**
 * 具体组件
 * Created by Administrator on 2016/12/9 0009.
 */
public class HouseBlend extends Beverage {
    public HouseBlend(){
        description = "House Blend Coffee";
    }
    public HouseBlend(int size){
        this();
        super.size = size;
    }

    public double cost() {
        return 9.9;
    }
}
