package designpattern.agent;

import java.rmi.Naming;

/**
 * 客户端
 * Created by Administrator on 2016/12/26 0026.
 */
public class MyRemoteClient {
    public static void main(String[] args) {
        new MyRemoteClient().go();
    }

    public void go(){
        try {
            MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
            String s = service.sayHello();
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
