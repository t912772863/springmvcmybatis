package romoteproxy;

/**
 * Created by Administrator on 2016/12/29 0029.
 */
public class SimpleClient {
    /**
     * 通过代理类调用远程方法
     */
    public void invoke(){
        HelloService hello = (HelloService) MyProxyFactory.getProxy(HelloService.class,"romoteproxy.HelloServiceImpl","localhost", 8002);
        String result = hello.echo("music");
        System.out.println("result=" + result);
    }

    public static void main(String[] args){
        SimpleClient client = new SimpleClient();
        client.invoke();
    }
}
