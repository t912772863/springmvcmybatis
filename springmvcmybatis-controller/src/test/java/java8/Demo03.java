package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Administrator on 2017/12/27 0027.
 */
public class Demo03 {

    public static void main(String[] args) {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

//        System.out.println("Languages which starts with J :");
//        filter(languages, (str)->str.startsWith("J"));

//        System.out.println("Languages which ends with a ");
//        filter(languages, (str)->str.endsWith("a"));

        System.out.println("Print all languages :");
//        filter(languages, (str)->true);

        System.out.println("Print no language : ");
//        filter(languages, (str)->false);

//        System.out.println("Print language whose length greater than 4:");
//        filter(languages, (str)->str.length() > 4);
    }


//    public static void filter(List<String> names, Predicate condition) {
//        for(String name: names)  {
//            if(condition.test(name)) {
//                System.out.println(name + " ");
//            }
//        }
//    }
}