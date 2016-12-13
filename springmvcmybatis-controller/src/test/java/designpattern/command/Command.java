package designpattern.command;

/**
 * 命令接口
 * Created by Administrator on 2016/12/12 0012.
 */
public interface Command {
    /**
     * 执行方法
     */
    void execute();

    /**
     * 撤销方法
     */
    void undo();
}
