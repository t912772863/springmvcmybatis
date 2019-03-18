package designpattern.cglibproxy;

import java.util.Random;

/**
 * Created by tianxiong on 2019/3/18.
 */
public class Persion {
    public Persion(){
        System.out.println("Persion 构造方法");
    }

    public final String getName(){
        return "my name is tianxiong";
    }

    public int getAge(){
        return new Random().nextInt(100);
    }
}
