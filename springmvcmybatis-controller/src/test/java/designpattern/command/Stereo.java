package designpattern.command;

/**
 * 音箱类
 * Created by Administrator on 2016/12/13 0013.
 */
public class Stereo {
    public void on(){
        System.out.println("音箱打开了");
    }

    public void setCD(){
        System.out.println("装进去了一盘CD");
    }

    public void setVolume(Integer value){
        System.out.println("声音设置为: "+value);
    }

    public void off(){
        System.out.println("关闭音箱");
    }

}
