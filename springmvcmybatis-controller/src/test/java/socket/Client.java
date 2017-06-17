package socket;

import java.io.*;
import java.net.Socket;

/**
 * socket通讯的客户端
 * Created by Administrator on 2017/6/17 0017.
 */
public class Client {
    public static void main(String[] args) {

        try {
            //1、创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("127.0.0.1",8888);
            //2、获取输出流，向服务器端发送信息
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("name:tianxiong,password:123456");
            printWriter.flush();
            socket.shutdownOutput();
            //3、获取输入流，并读取服务器端的响应信息
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info;
            while ((info = bufferedReader.readLine())!=null){
                System.out.println("服务说: "+info);
            }

            //4、关闭资源
            inputStream.close();
            inputStreamReader.close();
            outputStream.close();
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
