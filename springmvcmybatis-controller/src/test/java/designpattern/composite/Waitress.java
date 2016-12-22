package designpattern.composite;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class Waitress {
    MenuComponeny allMenus;

    public Waitress(MenuComponeny allMenus) {
        this.allMenus = allMenus;
    }

    public void printMenu(){
        allMenus.print();
    }
}
