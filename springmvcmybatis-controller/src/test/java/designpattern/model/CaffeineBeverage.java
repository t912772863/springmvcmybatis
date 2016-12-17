package designpattern.model;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public abstract class CaffeineBeverage {
    public final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        if(!needYuanWei()){
            addCondiments();
        }

    }

    abstract void brew();

    abstract void addCondiments();

    void boilWater(){
        System.out.println("烧水啦...");
    }

    void pourInCup(){
        System.out.println("倒进杯子里");
    }

    /**
     * 是否要原味饮料,这样就不会加其它东西进去
     *
     * 这个方法可以叫做钩子方法,用来影响模版方法中的某些非必要步骤,
     * 子类可以选择是否重写该方法
     * @return
     */
    boolean needYuanWei(){
        return false;
    }
}
