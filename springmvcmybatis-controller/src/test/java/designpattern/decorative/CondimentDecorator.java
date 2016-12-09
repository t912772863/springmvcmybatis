package designpattern.decorative;

/**
 * 调料抽象类
 *
 * 首先调料类必需能够替代饮料,所以继承饮料类
 * Created by Administrator on 2016/12/9 0009.
 */
public abstract class CondimentDecorator extends Beverage {

    /**
     * 每一个装饰者都必须重写该方法
     * @return
     */
    public abstract String getDescription();
}
