package designpattern.decorative;

/**
 * 豆浆装饰者
 * Created by Administrator on 2016/12/9 0009.
 */
public class Soy extends CondimentDecorator {
    Beverage beverage;
    public Soy(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription() {
        return beverage.getDescription()+", Soy";
    }

    public double cost() {
        return beverage.cost()+2;
    }
}
