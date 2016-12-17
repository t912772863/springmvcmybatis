package designpattern.model;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
public class Tea extends CaffeineBeverage {
    void brew() {
        System.out.println("放点茶叶");
    }

    void addCondiments() {
        System.out.println("加点柠檬,不就是柠檬茶了?");
    }

    /**
     * 茶还是原味的好,不要加乱攻八糟的东西了
     * @return
     */
    @Override
    public boolean needYuanWei(){
        return true;
    }
}
