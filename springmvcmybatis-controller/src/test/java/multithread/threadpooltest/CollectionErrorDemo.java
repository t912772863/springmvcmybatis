package multithread.threadpooltest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * java5以前的集合并发操作存在的问题
 * Created by Administrator on 2017/5/15 0015.
 */
public class CollectionErrorDemo {
    public static void main(String[] args) {
        // 普通的集合存在并发问题, 所以不能要循环中操作数据
//        Collection collection = new ArrayList();

        // 用下面这个线程安全的就没有问题了
        Collection collection = new CopyOnWriteArrayList();

        collection.add(new User(28,"张三"));
        collection.add(new User(25,"李四"));
        collection.add(new User(31,"王五"));

        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println("----------->");
            User user = (User)iterator.next();
            if("张三".equals(user.getName())){
                collection.remove(user);
            }else{
                System.out.println(user.toString());
            }
        }

    }

    static class User{
        private int age;
        private String name;
        public User(int age, String name){
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
