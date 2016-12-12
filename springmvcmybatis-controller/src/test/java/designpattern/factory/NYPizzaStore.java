package designpattern.factory;

/**
 * 纽约比萨店
 * Created by Administrator on 2016/12/10 0010.
 */
public class NYPizzaStore extends PizzaStore {
    protected Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new NYStyleCheesePizza();
        }else if(type.equals("veggie")){
            return new NYStyleVeggiePizza();
        }else if(type.equals("clam")){
            return new NYStyleClamPizza();
        }else if(type.equals("pepperoni")){
            return new NYStylePepperoniPizza();
        }else {
            return null;
        }
    }
}
