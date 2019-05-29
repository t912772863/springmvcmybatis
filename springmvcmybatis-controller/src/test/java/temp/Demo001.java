package temp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class Demo001<E> {
    private static final Logger log = LoggerFactory.getLogger(Demo001.class);

    private static class  Tab{
        private byte[] bytes = new byte[1024*1024*1024];
    }

    public static void main(String[] args) {
        try{
            String a = "";
            if(1==1){
                a = null;
                a.substring(0,5);
            }
        }catch (Exception e){
//            log.error("error, str:{}, e:{}", "a", e);
            log.error("====================");
            log.error("error", e);
        }

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
