package designpattern.composite;



import java.util.ArrayList;
import java.util.Iterator;

/**
 * 实现组合菜单
 * Created by Administrator on 2016/12/21 0021.
 */
public class Menu extends MenuComponeny{
    ArrayList<MenuComponeny> menuComponenies = new ArrayList<MenuComponeny>();
    String name;
    String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void add(MenuComponeny menuComponeny){
        menuComponenies.add(menuComponeny);
    }

    public void remove(MenuComponeny menuComponeny){
        menuComponenies.remove(menuComponeny);
    }

    public MenuComponeny getChile(int i){
        return menuComponenies.get(i);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Iterator createIterator(){
        return menuComponenies.iterator();
    }

    public void print(){
        System.out.println("--------"+getName());
        System.out.println("--------"+getDescription());
        // 用递归,打印菜单本身,以及所有的子菜单
        Iterator<MenuComponeny> iterator = menuComponenies.iterator();
        while (iterator.hasNext()){
            MenuComponeny menuComponeny = iterator.next();
            menuComponeny.print();
        }
    }
}
