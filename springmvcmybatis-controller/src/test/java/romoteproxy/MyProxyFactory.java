package romoteproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class MyProxyFactory {
    /**
     * 获取动态代理类
     * @param classType 接口
     * @param className 实现类名称
     * @param ip        ip 地址
     * @param port      端口
     * @return
     */
    @SuppressWarnings(value="unchecked")
    public static Object getProxy(Class classType,
                                  final String className,
                                  final String ip,final int port){
        // start
        InvocationHandler handler = new InvocationHandler(){
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                System.out.println("--- before " + method.getName() + "---");
                TcpConnector connector = new TcpConnector(ip, port);
                Call call = new Call(className,method.getName(),
                        method.getParameterTypes(),args);
                call = (Call)connector.invoke(call);
                Object result = call.getResult();
                if(result instanceof Throwable){
                    return new RemoteException(method.getName() + "invoke failed",
                            (Throwable)result);
                }
                System.out.println("--- after " + method.getName() + "---");
                connector.close();
                return result;
            }
        };
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{classType}, handler);
    }
}
