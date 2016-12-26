package designpattern.agent;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 制作一个远程的实现,
 *
 * 1, 实现远程接口,也就是自定义的远程接口
 * 2. 扩展UnicastRemoteObject.为了成为一个远程对象,该对象必须有远程功能,最简单的方式就是继承这个类,让超类来实现这些事情.
 * 3. 在构造器中拋出父类的异常
 * 4. 用RMI Registry注册此服务,使用Naming类的Rebind方法
 * Created by Administrator on 2016/12/26 0026.
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote{

    /**
     * 超类中的构造方法拋出了异常,所以子类也必须在构造方法中拋出此异常
     * @throws RemoteException
     */
    public MyRemoteImpl() throws RemoteException{}
    public String sayHello() throws RemoteException {
        return "服务器说: 你好!";
    }

    public static void main(String[] args) {
        try{
            MyRemote server = new MyRemoteImpl();
            /*
            为你的服务命名,好让客户在注册表中找到它,
            并在RMI register中注册此名字和服务, 当你绑定(bind)
            服务对象时, RMI会把服务换成stub,然后把stub放到register中.
             */
            Naming.bind("RemoteHello",server);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
