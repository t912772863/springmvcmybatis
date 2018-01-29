package java8;

/**
 * Lambda表达式简化替换匿名类
 * Created by Administrator on 2017/12/27 0027.
 */
public class Demo01 {
    public static void main(String[] args) {
        // java8以前, 要实例化一个匿名类
        new Thread(new Runnable() {
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();
        // java8以后, 可以一个Lambda表达式简化  () ->代表了整个匿名类
        new Thread(() -> System.out.print("In Java8, Lambda expression rocks !!")).start();





    }

}
