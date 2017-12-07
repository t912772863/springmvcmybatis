package temp;

import java.io.*;
import java.net.Socket;

/**
 * java调用TCP接口,输入命令
 * Created by Administrator on 2017/12/6 0006.
 */
public class SocketUtil  {
    /*
  * 获取socket连接
  */
    public static Socket getConnection(String ip, int port) {
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(30000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    /*
     * 发送报文
     */
    public static String send(Socket socket, String cmd) {
        InputStream in = null;
        OutputStream out = null;
        BufferedReader br = null;
        try {
            // in代表服务器到客户端的流
            in = socket.getInputStream();
            // 代表客户端到服务器的流
            out = socket.getOutputStream();

            // 输入执行命令
            PrintWriter printWriter = new PrintWriter(out, true);
            printWriter.println(cmd);
            printWriter.flush();

            // 接收执行命令结果
            br = new BufferedReader(new InputStreamReader(in));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /*
    * 关闭socket连接
    */
    public static void close(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
   * 测试代码
   */
    public static void main(String args[]){
        String result="";
        Socket socket=null;
        String cmd="SHOW";
        try {
            socket=SocketUtil.getConnection("file.servicedm.com", 55670);
            String resultStr=SocketUtil.send(socket, cmd);
            String resultArr[]=resultStr.split(" ");
            for (String s:resultArr) {
                System.out.println(s);
            }
        } catch (Exception e) {
            SocketUtil.close(socket);
        }
    }



}
