package designpattern.command;

/**
 * 打开电灯的命令对象
 * Created by Administrator on 2016/12/12 0012.
 */
public class LightOnCommand implements Command{
    Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }

    public void execute() {
        light.on();
    }

    public void undo() {
        light.off();
    }
}
