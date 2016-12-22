package designpattern.composite;

import java.util.Iterator;

/**
 * 菜单项
 * Created by Administrator on 2016/12/21 0021.
 */
public class MenuItem extends MenuComponeny{
    String name;
    String description;
    boolean vagetarain;
    double price;

    public MenuItem(String name, String description, boolean vagetarain, double price) {
        this.name = name;
        this.description = description;
        this.vagetarain = vagetarain;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVagetarain() {
        return vagetarain;
    }

    public void setVagetarain(boolean vagetarain) {
        this.vagetarain = vagetarain;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Iterator createIterator(){
        return null;
    }
    public void print() {
        System.out.println("MenuItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", vagetarain=" + vagetarain +
                ", price=" + price +
                '}');
    }
}
