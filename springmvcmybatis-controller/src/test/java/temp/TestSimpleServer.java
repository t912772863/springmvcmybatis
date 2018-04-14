package temp;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class TestSimpleServer {
    public static void main(String[] args) {
        try {
            SimpleHttpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
