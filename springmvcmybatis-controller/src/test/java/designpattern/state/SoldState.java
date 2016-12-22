package designpattern.state;

/**
 * 已经购买,等待出糖果状态
 * Created by Administrator on 2016/12/22 0022.
 */
public class SoldState implements State {
    GumballMachine gumballMachine;
    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
    public void insertQuarter() {
        System.out.println("等一下,先给你出个货再说");
    }

    public void ejectQuarter() {
        System.out.println("别瞎按");
    }

    public void turnCrank() {
        System.out.println("...不支持这个操作");
    }

    public void dispense() {
        gumballMachine.releaseBall();
        int count = gumballMachine.getCount();
        if(count>0){
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }else{
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }


}
