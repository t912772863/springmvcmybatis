package designpattern.state;

/**
 * 有币状态
 * Created by Administrator on 2016/12/22 0022.
 */
public class HasQuarterState implements State {
    GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    public void insertQuarter() {
        System.out.println("已经有币了,先不用投了");
    }

    public void ejectQuarter() {
        System.out.println("退币成功");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    public void turnCrank() {
        System.out.println("你已经转动开关了");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    public void dispense() {
        System.out.println("这个状态下不能执行分配操作");
    }


}
