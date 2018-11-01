package lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/10 0010.
 */
public class TestLambda2 {
    public static void main(String[] args) {
        List<Ab> list = new ArrayList<>();
        List<String> strList = new ArrayList<>();
        strList.add("12,tian");
        strList.add("20,zhang");

        Ab ab = new Ab();
        strList.forEach(s -> {
            ab.age = s.split(",")[0];
            ab.name=s.split(",")[1];
            list.add(ab);
        });
        System.out.println(list);

    }

    private static class Ab{
        public String age;
        public String name;
    }
}
