package designpattern.command;

/**
 * 电灯对象
 * Created by Administrator on 2016/12/12 0012.
 */
public class Light {
    String where;
    public Light(String where){
        this.where = where;
    }

    public void on(){
        System.out.println(where+"打开电灯");
    }
    public void off(){
        System.out.println(where+"关掉电灯");
    }
}
