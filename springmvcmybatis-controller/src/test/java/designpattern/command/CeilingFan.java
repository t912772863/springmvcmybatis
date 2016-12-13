package designpattern.command;

/**
 * 吊扇
 * Created by Administrator on 2016/12/13 0013.
 */
public class CeilingFan {
    public static final int HIGHT = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    String location;
    int speed;

    public CeilingFan(String location){
        this.location = location;
        speed = OFF;
    }

    public void hight(){
        this.speed = HIGHT;
    }

    public void medium(){
        this.speed = MEDIUM;
    }

    public void low(){
        this.speed = LOW;
    }

    public void off(){
        this.speed = OFF;
    }

    public int getspeed(){
        return this.speed;
    }

}
