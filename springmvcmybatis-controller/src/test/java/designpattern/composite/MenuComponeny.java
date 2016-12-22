package designpattern.composite;

import java.util.Iterator;

/**
 * 菜单组件对象
 * Created by Administrator on 2016/12/21 0021.
 */
public abstract class MenuComponeny {
    public void add(MenuComponeny menuComponeny){
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponeny menuComponeny){
        throw new UnsupportedOperationException();
    }

    public MenuComponeny getChild(int i){
        throw new UnsupportedOperationException();
    }

    public String getName(){
        throw new UnsupportedOperationException();
    }

    public String getDiscription(){
        throw new UnsupportedOperationException();
    }

    public double getPrice(){
        throw new UnsupportedOperationException();
    }

    public void isVagetarrain(){
        throw new UnsupportedOperationException();
    }

    public void print(){
        throw new UnsupportedOperationException();
    }

    public Iterator createIterator(){
        throw new UnsupportedOperationException();
    }


}
