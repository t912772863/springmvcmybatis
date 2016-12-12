package designpattern.factory;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza(){
        name = "NY style sauce and cheese pizza";
        dough = "比较薄的饼";
        sauce = "蕃茄酱";
        toppings.add("grated reggiano cheese");
    }

}
