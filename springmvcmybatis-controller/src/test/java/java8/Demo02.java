package java8;

import java.util.Arrays;
import java.util.List;

/**
 * Lambda表达式简化 替换循环
 * Created by Administrator on 2017/12/27 0027.
 */
public class Demo02 {

    public static void main(String[] args) {
        // 以前如果要对集合中的所有元素做同一操作, 则要循环遍历
        List<String> list = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        for (String s : list){
            System.out.println(s);
        }

        // 现在可以简化为.  使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示
//        list.forEach(System.out :: println);



    }



}
