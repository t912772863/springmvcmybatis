package designpattern.factory;

/**
 * 比萨店
 * Created by Administrator on 2016/12/10 0010.
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type){
        Pizza pizza = null;
        pizza = createPizza(type);

        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    protected abstract Pizza createPizza(String type);

}
