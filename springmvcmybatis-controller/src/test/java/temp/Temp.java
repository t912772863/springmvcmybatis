package temp;

/**
 * Created by tianxiong on 2019/8/2.
 */
public class Temp {
    private volatile int a = 1;

    public static void main(String[] args) {
        Temp temp = new Temp();
        System.out.println(temp.test());
    }

    public int test(){
        int a = 1;
        int b = 2;
        int c = (a+b)*10;
        return c;
    }
}
