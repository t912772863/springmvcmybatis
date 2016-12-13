package designpattern.command;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();


        Light light = new Light("客厅");
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        remoteControl.setCommand(1,lightOnCommand,null);

        Stereo stereo = new Stereo();
        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        remoteControl.setCommand(2,stereoOnWithCDCommand,null);

        remoteControl.onButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);

        remoteControl.undoButtonWasPushed();

    }

}
