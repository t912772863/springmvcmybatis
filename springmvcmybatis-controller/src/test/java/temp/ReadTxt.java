package temp;

import com.tian.springmvcmybatis.dao.entity.File;
import com.tian.common.util.FileUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public class ReadTxt {
    public static void main(String[] args) throws Exception {
        // 有效号码集
        List<String> mobileList = new ArrayList<String>();

        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("E:\\yuanwenjian.txt"));
        File file = new File();


        int tottalLines = FileUtil.getTxtLineNumber("E:\\yuanwenjian.txt");
        Pattern pattern = Pattern.compile("^(1[0-9]{10})$");
        for (int i = 1; i <= tottalLines; i++) {
            // 拿到每行的内容
            String str = lineNumberReader.readLine();
            // 拿到第一个","前的字符串
            str = str.substring(0,str.indexOf(","));
            if(str.startsWith("86")){
                str.replace("86","");
            }
            if(pattern.matcher(str).find()){
                mobileList.add(str);
            }
        }
        // 把有效号码集写入新的文件中.
        FileWriter fw = new FileWriter("E:/"+System.currentTimeMillis()+".txt");
        for (String s : mobileList) {
            fw.write(s);
            fw.write("\r\n");//写入换行
        }
        fw.close();

    }
}
