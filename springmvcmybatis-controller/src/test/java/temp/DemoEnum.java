package temp;

/**
 * Created by Administrator on 2017/10/16 0016.
 */
public enum DemoEnum implements IEnum{
    ZHANGSAN(10,"zhangesan"),
    LISI(11,"lisi");

    private int age;
    private String name;

    DemoEnum(int age, String name){
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
        return "DemoEnum{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 通过某一个属性值获取对应在的常量
     * @param age
     * @return
     */
    public static DemoEnum getEnumByAge(int age){
        DemoEnum[] enums = DemoEnum.values();
        for (DemoEnum d:enums) {
            if(age == d.getAge()){
                return d;
            }
        }
        return null;
    }
}
