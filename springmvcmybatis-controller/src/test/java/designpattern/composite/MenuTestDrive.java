package designpattern.composite;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class MenuTestDrive {
    public static void main(String[] args) {
        MenuComponeny menu1 = new Menu("早点菜单","这是一个早点菜单");
        MenuComponeny menu2 = new Menu("午餐菜单","这是一个午餐菜单");
        MenuComponeny menu3 = new Menu("饮品菜单","这是一个饮品菜单");

        MenuComponeny menu = new Menu("总菜单","这是一个总菜单");
        menu.add(menu1);
        menu.add(menu2);
        menu.add(menu3);

        menu1.add(new MenuItem("豆浆","现磨豆浆",true,2.00));
        menu2.add(new MenuItem("鱼香茄子","好吃,热卖",true,15.00));
        menu3.add(new MenuItem("奶茶","新品上市",true,8.50));

        Waitress waitress = new Waitress(menu);
        waitress.printMenu();
    }
}
