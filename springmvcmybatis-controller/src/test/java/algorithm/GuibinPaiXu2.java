package algorithm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 对一亿个数排序
 *
 * Created by Administrator on 2018/6/29 0029.
 */
public class GuibinPaiXu2 {

    public static void main(String[] args) throws IOException {
        scsz(100000000,"F:/tempshuzi.txt");
    }

    /**
     * 生产无序数字到一个文件中
     * @param count 数字个数
     * @param filePath 生成的文件路径
     */
    public static void scsz(int count, String filePath) throws IOException {
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
        OutputStream out = new FileOutputStream(file);
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            int n = random.nextInt(count);
            out.write((n+"\r\n").getBytes());
        }



    }
}
