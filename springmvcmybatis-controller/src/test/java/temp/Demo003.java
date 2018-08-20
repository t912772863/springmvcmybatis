package temp;


import java.io.*;

/**
 * 动态规划
 */
public class Demo003 {

    public static void main(String[] args) throws IOException {
        String path = "E:\\临时文件夹\\300w.txt";
        File f = new File(path);
        OutputStream out = new FileOutputStream(f);
        long phone = 13500000000L;
        for (int i = 0; i < 3000000; i++) {
            String lineText = ""+phone+", 13459,AQWE4,wwaddfff,AAAAAA3,123458,020-88888890,7月9日,11111111.59"+"\r\n";
            phone++;
            out.write(lineText.getBytes());

        }
        out.close();
    }


}
