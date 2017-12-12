package getpath;


import java.io.File;
import java.io.IOException;

/**
 * 不同的方法获取不同的路径示例
 * Created by Administrator on 2017/5/4 0004.
 */
public class GetPathTest {
    public static void main(String[] args) throws IOException {
        GetPathTest getPathTest = new GetPathTest();

        /*
        获取到class文件的绝对路径
        方法获得当前生成的class的绝对路径(此方法在jar包中无效，因为他获得的是生成的class的路径，返回的内容最后包含/)
         */
        String path1 = GetPathTest.class.getResource("").getPath();
        System.out.println("path1: "+path1);

        // 根路径, jar中无效
        String path8 = GetPathTest.class.getResource("/").getPath();
        System.out.println("path8: "+path8);


        // 获取当前目录路径, jar中无效
        String path2 = getPathTest.getClass().getResource(".").getFile().toString();
        System.out.println("path2: "+path2);

        // 获取根路径, jar中无效
        String path3 = getPathTest.getClass().getResource("/").getFile().toString();
        System.out.println("path3: "+path3);

        // 获取当前路径的上一级路径, jar中无效
        String path4 = getPathTest.getClass().getResource("../").getFile().toString();
        System.out.println("path4: "+path4);

        // 得到项目文件夹的根目录，不带/, 其中"user.dir"是固定写法
        String path5 = System.getProperty("user.dir");
        System.out.println("path5: "+path5);

        // 利用File提供的函数获取当前路径.
        File file = new File(""); //设定为当前文件夹

        // 获取标准路径
        String path6 = file.getCanonicalPath();
        System.out.println("path6: "+path6);

        // 获取绝对路径
        String path7 = file.getAbsolutePath();
        System.out.println("path7: "+path7);

        // jsp中:
//        request.getContextPath()
//        request.getSession().getServletContext().getRealPath()

        // servlet中:
//        this.getServletContext().getRealPath("/");
//        this.getServlet().getServletContext().getRealPath("/");

    }
}
