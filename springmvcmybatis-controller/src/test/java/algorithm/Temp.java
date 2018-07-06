package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/7/5 0005.
 */
public class Temp {
    public static void main(String[] args) {
      List<Integer> list = new ArrayList<Integer>();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(1));
        System.out.println(list);

        Set<Integer> set = new HashSet<>();
        set.add(new Integer(1));
        set.add(new Integer(2));
        set.add(new Integer(1));
        System.out.println(set);
        set.toArray();
    }
}
