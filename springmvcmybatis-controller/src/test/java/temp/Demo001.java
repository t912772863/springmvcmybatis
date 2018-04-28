package temp;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 */
public class Demo001<E> {


    public static void main(String[] args) {
        long startTime = System.nanoTime();
        long startTime2 = System.currentTimeMillis();
        System.out.println(startTime2);
        DemoEnum[] arr = DemoEnum.values();
//        for (DemoEnum d : arr){
//            System.out.println(d.toString());
//        }


        DemoEnum demoEnum = DemoEnum.getEnumByAge(10);
        System.out.println(demoEnum);

        Map<DemoEnum, Set<DemoEnum>> map = new EnumMap<DemoEnum, Set<DemoEnum>>(DemoEnum.class);
        System.out.println(System.nanoTime() - startTime);
        System.out.println(System.currentTimeMillis() - startTime2);
        char c = 46;
        System.out.println(c+"");
    }

    public static void testEnum(DemoEnum a){
        System.out.println(a.toString());
    }


}
