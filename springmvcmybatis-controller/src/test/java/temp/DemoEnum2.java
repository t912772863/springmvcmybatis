package temp;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
public enum  DemoEnum2 implements IEnum{
    X1("1","1"),
    X2("1","0");


    private String a;
    private String b;
    private DemoEnum2(String a,String b){
        this.a = a;
        this.b = b;
    }
}
