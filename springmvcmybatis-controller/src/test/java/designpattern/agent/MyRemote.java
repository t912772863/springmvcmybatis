package designpattern.agent;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 制作一个远程接口, remote是一个记号接口, 所以它不具有方法, 对于RMI来说, Remote具有特别的意义, 所以我们必须遵守规则.
 *
 * Created by Administrator on 2016/12/26 0026.
 */
public interface MyRemote extends Remote {
    /**
     * 定义一个远程方法
     * 因为远程方法涉及到了网络io,所以很有可能会出现异常,所以都要声明RemoteException
     *
     * 因为在传输的过程中要对数据进行序列化,所以要保证返回的类型是原语类型,如果是自定义的数据类型,则要确保,这个类型要以被序列化
     * @return
     * @throws RemoteException
     */
    String sayHello() throws RemoteException;
}
