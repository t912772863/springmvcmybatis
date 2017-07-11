package springstudy.knights;

import java.io.PrintStream;

/**
 * Created by Administrator on 2017/6/20 0020.
 */
public class SlayDragonQuest implements Quest{
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    public void embark() {
        stream.print("任务执行啦");
    }
}
