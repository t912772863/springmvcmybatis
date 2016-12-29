package designpattern.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * 测试配对服务
 * Created by Administrator on 2016/12/29 0029.
 */
public class MachMakingTestDrive {
    public MachMakingTestDrive() {
        initData();
    }

    private void initData() {
    }

    public static void main(String[] args) {
        MachMakingTestDrive test = new MachMakingTestDrive();
        test.drive();
    }

    private void drive() {
        PersonBean joe = new PersonBeanImpl();
        joe.setGender("男");
        joe.setHotOrRating(100);
        joe.setName("Joe");

        PersonBean owner = ProxyUtil.getOwnerProxy(joe);
        System.out.println("名字: "+owner.getName());
        owner.setInterests("游泳");
        try{
            owner.setHotOrRating(100);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("自己不能设置热度");
        }

        PersonBean notOwner = ProxyUtil.getNotOwnerProxy(joe);
        try{
            notOwner.setName("小明");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("人家的名字,不能乱改");
        }
        notOwner.setHotOrRating(80);

        System.out.println(joe);
        // 查看某个对象是否为代理对象,
        System.out.println(Proxy.isProxyClass(owner.getClass()));
    }
}
