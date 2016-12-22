package designpattern.state;

/**
 * 状态接口
 * Created by Administrator on 2016/12/22 0022.
 */
public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
