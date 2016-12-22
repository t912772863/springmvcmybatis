package designpattern.state;

/**
 * 无币状态
 * Created by Administrator on 2016/12/22 0022.
 */
public class NoQuarterState implements State {
    GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("投币成功");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    public void ejectQuarter() {
        System.out.println("没投币,退个毛线");
    }

    public void turnCrank() {
        System.out.println("亲,请先投币");
    }

    public void dispense() {
        System.out.println("亲,请先投币");
    }
}
