package designpattern.command;

/**
 * 空命令
 * Created by Administrator on 2016/12/12 0012.
 */
public class NoCommand implements Command {
    public void execute() {
        System.out.println("没有命令");
    }

    public void undo() {

    }
}
