package designpattern.command;

import java.util.Arrays;

/**
 * 实现遥控器
 * Created by Administrator on 2016/12/12 0012.
 */
public class RemoteControl {
    /**
     * 这个时候,遥控器要处理7个开与关的命令,用一个数组记录对应的命令
     */
    Command[] onCommands;
    Command[] offCommands;

    /**
     * 记录最后一次操作,以便实现撤销操作
     */
    Command undoCommand;

    /**
     * 在构造器中初始化命令数组
     */
    public RemoteControl(){
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command command = new NoCommand();
        for(int i = 0; i<7; i++){
            onCommands[i] = command;
            offCommands[i] = command;
        }

        undoCommand = command;
    }

    /**
     * 添加命令到遥控器中
     * 三个参数,分别是卡槽的位置, 开的命令, 关的命令
     * 这些命令将记录在开关数组中对应的位置, 以供稍后使用.
     * @param slot
     * @param onCommand
     * @param offCommand
     */
    public void setCommand(int slot, Command onCommand, Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    /**
     * 打开开关方法
     * @param slot
     */
    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
        this.undoCommand = onCommands[slot];
    }

    /**
     * 关闭开关方法
     * @param slot
     */
    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
        this.undoCommand = offCommands[slot];
    }

    /**
     * 按下撤销按钮
     */
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }

    @Override
    public String toString() {
        return "RemoteControl{" +
                "onCommands=" + Arrays.toString(onCommands) +
                ", offCommands=" + Arrays.toString(offCommands) +
                '}';
    }
}
