package temp;

import org.apache.commons.io.output.WriterOutputStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class Demo001<E> {
    private static class  Tab{
        private byte[] bytes = new byte[1024*1024*1024];
    }


    public static void main(String[] args) throws IOException {
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
