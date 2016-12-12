package designpattern.factory;

/**
 * 一个比萨工厂类
 * Created by Administrator on 2016/12/10 0010.
 */
public class SimplePizzaFactory {
    /**
     * 首先在这个工厂中定义一个方法,所有的客户都用这个方法来实例化对象
     * @param type
     * @return
     */
    public Pizza createPizza(String type){
        Pizza pizza = null;
        if(type.equals("cheese")){
            pizza = new CheesePizza();
        }
        return pizza;
    }

}
