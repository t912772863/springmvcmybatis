package designpattern.command;

/**
 * 简单的遥控器
 * Created by Administrator on 2016/12/12 0012.
 */
public class SimpleRemoteControl {
    Command slot;
    public SimpleRemoteControl(){}

    public void setCommand(Command command){
        slot = command;
    }

    public void buttonWasPressed(){
        slot.execute();
    }

}
