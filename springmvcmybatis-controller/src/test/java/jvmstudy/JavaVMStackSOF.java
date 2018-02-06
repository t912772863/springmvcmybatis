package jvmstudy;

/**
 * 虚拟机栈和本地方法栈溢出.
 *
 * 如果线程请求尝试大于栈允许的深度: StackOverflowError异常.
 * 如果在多线程时, 新的线程申请不到内存则: OutOfMemoryError异常. unable to create new native thread
 *
 * -Xoss参数用来设置本地方法栈的大小. 但是对于HotSpot虚拟机, 不区分虚拟机栈和本地方法栈, 该参数是无效的.
 * -Xss 用来设置虚拟机栈的大小.
 * Created by Administrator on 2018/2/1 0001.
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    /**
     * 无限制递归, 出现栈深圳溢出
     */
    public void stackLeak(){
        stackLength++;
        stackLeak();
        System.out.println("==========================stack length: "+stackLength);
    }

    public static void main(String[] args) {
        // 无限制递归, 出现栈深圳溢出
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try{
            oom.stackLeak();
        }catch (Throwable e){
            e.printStackTrace();
        }


        // 不停的创建线程, 当虚拟机栈没有可分配的内存时, 出现OOM
//        while (true){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    // 防止线程完成任务结束
//                    while (true){
//
//                    }
//                }
//            }).start();
//        }

    }

}
