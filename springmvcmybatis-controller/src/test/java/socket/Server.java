package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket学习的服务端
 * Created by Administrator on 2017/6/17 0017.
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8888);
            //2、调用accept()方法开始监听，等待客户端的连接
            Socket socket = serverSocket.accept();
            //3、获取输入流，并读取客户端信息
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info;
            while ((info = bufferedReader.readLine()) != null){
                System.out.println("客户端说: "+ info);
            }
            // 关闭输入流
            socket.shutdownInput();

            // 4、获取输出流，响应客户端的请求
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("大家好");
            printWriter.flush();

            // 5. 关闭资源
            outputStream.close();
            printWriter.close();
            socket.close();
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
