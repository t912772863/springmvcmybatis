package designpattern.decorative;

/**
 * 饮料类
 * Created by Administrator on 2016/12/9 0009.
 */
public abstract class Beverage {
    String description = "未知的饮料";
    /**
     * 饮料尺寸
     * 1: 小;  2中,  3大  默认中杯
     */
    int size = 2;

    /**
     * 描述饮料
     * @return
     */
    public String getDescription() {
        return description;
    }

    public int getSize() {
        return size;
    }

    /**
     * 获取饮料的价钱
     * @return
     */
    public abstract double cost();
}
