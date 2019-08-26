package temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 */
public class Demo001<E> {
    private static final Logger log = LoggerFactory.getLogger(Demo001.class);

    private static class  Tab{
        private byte[] bytes = new byte[1024*1024*1024];
    }

    public static void main(String[] args) {
        Set<String> sets = new HashSet<>();
        sets.add("a");
        sets.add("b");
        sets.add("a");
        System.out.println(sets);
    }


    public static void main2(String[] args) throws IOException {
        List<Tab> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(new Tab());
        }
        System.out.println(list.size());
    }

    public static void testEnum(DemoEnum a){
        System.out.println(a.toString());
    }


}
