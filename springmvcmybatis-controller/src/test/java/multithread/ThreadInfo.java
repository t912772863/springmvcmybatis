package multithread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Created by Administrator on 2018/2/28 0028.
 */
public class ThreadInfo {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        java.lang.management.ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for (java.lang.management.ThreadInfo t:threadInfos
             ) {
            System.out.println("thread id: "+t.getThreadId()+";  thread name: "+t.getThreadName());
        }

    }
}
