package temp;

import org.apache.commons.io.output.WriterOutputStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 */
public class Demo001<E> {


    public static void main(String[] args) throws IOException {
//        http://121.15.167.235:8982/HeSmsCloud/smsTemplate/insertSmsTemplate
        File file = new File("F://testip.txt");
        OutputStream out = new WriterOutputStream(new FileWriter(file));
        for(int i=0;i< 100000 ; i++){
            out.write(("http://121.15.167.235:8982/HeSmsCloud/smsTemplate/insertSmsTemplate?a="+i+"\r\n").getBytes());
        }
        out.close();

        String s = "";

    }

    public static void testEnum(DemoEnum a){
        System.out.println(a.toString());
    }


}
