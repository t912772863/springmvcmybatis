package springstudy.knights;

import java.io.PrintStream;

/**
 * 吟游诗人, 记录骑士的事迹
 * Created by Administrator on 2017/6/20 0020.
 */
public class Minstrel {
    private PrintStream stream;

    public Minstrel(PrintStream stream) {
        this.stream = stream;
    }

    public void singBeforeQuest(){
        stream.print("上吧, 勇敢的骑士");
    }

    public void singAfterQuest(){
        stream.print("胜利归来,勇敢的骑士");
    }
}
