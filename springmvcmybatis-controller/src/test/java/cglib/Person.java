package cglib;

/**
 * Created by tianxiong on 2019/3/25.
 */
public class Person {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String say(String msg){
        System.out.println("execute method say");
        return "someone say: "+msg;
    }

    public int grow(int i){
        this.age += i;
        System.out.println(this.age);
        return this.age;
    }
}
