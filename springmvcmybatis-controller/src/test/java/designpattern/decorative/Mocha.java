package designpattern.decorative;

/**
 * 摩卡
 *
 * 这是一个装饰者,所以要继承自Condimentdecorator
 * Created by Administrator on 2016/12/9 0009.
 */
public class Mocha extends CondimentDecorator{
    /**
     * 用一个实例变量记录饮料, 也就是被装饰者
     */
    Beverage beverage;

    /**
     * 把被装饰者记录到实例变量中
     * @param beverage
     */
    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    public String getDescription() {
        // 不仅描述了饮料,连调料也描述出来
        return beverage.getDescription()+", Mocha";
    }

    public double cost() {
        //根据大小杯算加调料的钱
        int size = beverage.getSize();
        double d = 0;
        if(1==size){
            // 调料的价格是写死的,可以用一个成员变量,赋值可以通过配置参数,或者构造方法进行初始化
            d = 2*0.5;
        }else if (2==size){
            d= 2;
        }else if(3== size){
            d = 2*2;
        }

        // 原来的饮料的价钱加上调料的价钱
        return beverage.cost()+d;
    }
}
