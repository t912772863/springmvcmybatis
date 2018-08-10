package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java8中lambda表达式入门文章: https://www.zhihu.com/question/20125256?answer_deleted_redirect=true
 * Created by Administrator on 2018/3/2 0002.
 */
public class Testlambda {
    public static void main(String[] args) {
        MylambdaInterface aBlockOfCode = (s) -> System.out.println(s);
        function1((s) -> System.out.println(s),"haha");


        function1(new MylambdaInterface() {
            @Override
            public void doSomeThing(String s) {
                System.out.println(s);
            }
        }, "hehe");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getId());
            }
        }).start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getId());
        }).start();

        List<String> strList = new ArrayList<String>();
        strList.add("aaaaa");
        strList.add("afeawe");
        strList.add("bfe");
        strList.add("caef");

        //遍历打印集合中所有以a开头的字符串.
        for (String s : strList){
            if(s.startsWith("a")){
                System.out.println(s);
            }
        }

       // 利用函数式接口包
        checkAndExecute(strList, (a)->a.startsWith("a"),(b)-> System.out.println(b));

        // 甩掉静态函数, 直接用stream();
        strList.stream().filter(a->a.startsWith("b")).forEach(b -> System.out.println(b));


    }


    public static void function1(MylambdaInterface a, String s){
        a.doSomeThing(s);
    }

    public static void checkAndExecute(List<String> list, Predicate<String> p, Consumer<String> c){
//        for (String s : list){
//            if(p.test(s)){
//               c.accept(s);
//            }
//        }

        // 用forEach()方法代替for each
        list.forEach((a)->{
            if(p.test(a))
                c.accept(a);
        });
    }
}
