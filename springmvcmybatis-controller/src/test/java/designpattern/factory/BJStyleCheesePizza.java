package designpattern.factory;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class BJStyleCheesePizza extends Pizza {
    public BJStyleCheesePizza(){
        name = "特色比萨";
        dough = "非常厚的饼";
        sauce = "大葱就够了";
        toppings.add("加点老干妈");
    }

    void cut(){
        System.out.println("东北爷们不用切,直接啃就行了");
    }
}
