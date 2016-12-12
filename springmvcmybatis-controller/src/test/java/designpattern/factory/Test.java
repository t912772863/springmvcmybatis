package designpattern.factory;

/**
 *
 * Created by Administrator on 2016/12/10 0010.
 */
public class Test {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore bjStore = new BJPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println(pizza.getName());

        pizza = bjStore.orderPizza("cheese");
        System.out.println(pizza.getName());
    }
}
