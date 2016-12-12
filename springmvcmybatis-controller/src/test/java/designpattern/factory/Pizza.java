package designpattern.factory;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public abstract class Pizza {
    String name;
    String dough;
    String sauce;
    ArrayList toppings = new ArrayList();

    void prepare(){
        System.out.println("Prepare "+ name);
        System.out.println("tossing dough   ");
        System.out.println("adding sauce");
        System.out.println("adding topping ... ");
        for(int i = 0; i< toppings.size(); i++){
            System.out.println("   "+ toppings.get(i));
        }
    }

    void bake(){
        System.out.println("bake ......");
    }

    void cut(){
        System.out.println("cut ......");
    }

    void box(){
        System.out.println("box ......");
    }

    public String getName() {
        return name;
    }
}
