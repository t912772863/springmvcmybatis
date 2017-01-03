package romoteproxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class TcpConnector {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    /**
     * 构造器
     * @param ip
     * @param port
     * @throws IOException
     */
    public TcpConnector(String ip,int port)throws IOException {
        socket = new Socket(ip,port);
    }
    /**
     * 调用远程方法
     * @param obj
     * @throws IOException
     */
    public Object invoke(Object obj)throws IOException,ClassNotFoundException{
        if(out == null){
            out = new ObjectOutputStream(socket.getOutputStream());
        }
        out.writeObject(obj);
        out.flush();
        if(in == null){
            in = new ObjectInputStream(socket.getInputStream());
        }
        return in.readObject();
    }
    /**
     * 关闭连接
     */
    public void close(){
        try{
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch(Exception e){
        }
    }
}
