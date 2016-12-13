package designpattern.command;

/**
 * 打开音响命令
 * Created by Administrator on 2016/12/13 0013.
 */
public class StereoOnWithCDCommand implements Command {
    Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo){
        this.stereo = stereo;
    }

    public void undo() {
        stereo.off();
    }

    public void execute(){
        stereo.on();
        stereo.setCD();
        stereo.setVolume(20);
    }

}
