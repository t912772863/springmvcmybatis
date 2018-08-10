package lambda;

/**
 * Created by Administrator on 2018/3/2 0002.
 */
@FunctionalInterface
public interface MylambdaInterface {
    /**
     * java8以后的语法, 接口可以有一个默认的方法, 用default关键字修饰.
     *
     * @return
     */
    default String a(){
        System.out.println("");
        return "";
    }

    void doSomeThing(String s);

}
