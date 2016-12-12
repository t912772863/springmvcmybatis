package designpattern.factory;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class BJPizzaStore extends PizzaStore {
    protected Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new BJStyleCheesePizza();
        }else if(type.equals("pepperoni")){
            return new BJStylePepperoniPizza();
        }else {
            return null;
        }
    }
}
