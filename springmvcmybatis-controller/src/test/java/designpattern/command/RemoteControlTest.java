package designpattern.command;

/**
 * 命令模式的客户
 * Created by Administrator on 2016/12/12 0012.
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl simpleRemoteControl = new SimpleRemoteControl();
        Light light = new Light("");
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        simpleRemoteControl.setCommand(lightOnCommand);
        simpleRemoteControl.buttonWasPressed();

        GarageDoor garageDoor = new GarageDoor();
        OpenDoorCommand openDoorCommand = new OpenDoorCommand(garageDoor);
        simpleRemoteControl.setCommand(openDoorCommand);
        simpleRemoteControl.buttonWasPressed();
    }
}
