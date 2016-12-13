package designpattern.command;

/**
 * Created by Administrator on 2016/12/12 0012.
 */
public class OpenDoorCommand implements Command {
    GarageDoor garageDoor;

    public OpenDoorCommand(GarageDoor garageDoor){
        this.garageDoor = garageDoor;
    }

    public void execute() {
        garageDoor.openDoor();
    }

    public void undo() {
        garageDoor.closeDoor();
    }
}
