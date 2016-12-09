package designpattern.decorative;

/**
 * 奶泡 装饰者
 * Created by Administrator on 2016/12/9 0009.
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;
    public Whip(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription()+", Whip";
    }

    public double cost() {
        return beverage.cost()+3;
    }
}
