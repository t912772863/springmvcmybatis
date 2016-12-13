package designpattern.command;

/**
 * 宏命令,也就是一组命令的集合
 *
 * todo 宏命令中的撤销也应该是把多个操作一起撤销,可以通解一个消息栈来存放这个宏中的多个命令对象
 * Created by Administrator on 2016/12/13 0013.
 */
public class MacroCommand implements Command{
    Command[] commands;

    public MacroCommand(Command[] commands){
        this.commands = commands;
    }

    /**
     * 执行宏命令的时候,依次执行里面的每一个命令
     */
    public void execute() {
        for (Command c : this.commands) {
            c.execute();
        }
    }

    public void undo() {

    }
}
