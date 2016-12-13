package designpattern.command;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class LightOffCommand implements Command{
    Light light;
    public LightOffCommand(Light light){
        this.light = light;
    }


    public void execute() {
        light.off();
    }

    public void undo() {
        light.on();
    }
}
