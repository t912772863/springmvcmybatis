package designpattern.command;

/**
 * 吊扇高速命令
 * Created by Administrator on 2016/12/13 0013.
 */
public class CeilingFanHightCommand implements Command{
    CeilingFan ceilingFan;
    /**
     * 之前的速度
     */
    int preSpeed;

    public CeilingFanHightCommand(CeilingFan ceilingFan){
        this.ceilingFan = ceilingFan;
    }

    public void execute() {
        // 先把之前的速度记录下来
        preSpeed = ceilingFan.getspeed();
        ceilingFan.hight();
    }

    public void undo() {
        if(preSpeed == CeilingFan.HIGHT){
            ceilingFan.hight();
        }else if(preSpeed == CeilingFan.MEDIUM){
            ceilingFan.medium();
        }else if(preSpeed == CeilingFan.LOW){
            ceilingFan.low();
        }else if(preSpeed == CeilingFan.OFF){
            ceilingFan.off();
        }
    }
}
