package spitest;

/**
 * Created by tianxiong on 2019/3/7.
 */
public class TestSpiTwo implements ITestSpi {
    @Override
    public String sayHello(String name) {
        return "hello "+name+", i am two.";
    }
}
