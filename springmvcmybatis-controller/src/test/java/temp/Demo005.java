package temp;

/**
 * 试一下String.format的效率问题.
 * 结论, 直接拼接, 在内存没有溢出时, 和StringBuilder速度差不多. String.format速度最慢.
 * 例如:下面的三个方法,500万次循环, 方法1用时50多秒, 方法2和方法3都用时30多秒.
 * Created by tianxiong on 2019/7/19.
 */
public class Demo005 {

    public static void main(String[] args) {
        test3();
    }

    private static void test1(){
        String s = "as%sdfa%ssdfaswesdgaeasdg%sadgadfgafgafgafga%sfgadfgafg";
        long start = System.currentTimeMillis();
        for (int i = 0; i <5000000 ; i++) {
            System.out.println( String.format(s, i+"", (i+1)+"", (i+2)+"", (i+3)+""));
        }
        System.out.println("use: "+ (System.currentTimeMillis() - start));
    }

    private static void test2(){
        long start = System.currentTimeMillis();
        for (int i = 0; i <5000000 ; i++) {
            System.out.println( "as"+i+"dfa"+(i+1)+"sdfaswesdgaeasdg"+(i+2)+"adgadfgafgafgafga"+(i+3)+"fgadfgafg");
        }
        System.out.println("use: "+ (System.currentTimeMillis() - start));
    }

    private static void test3(){

        long start = System.currentTimeMillis();
        for (int i = 0; i <5000000 ; i++) {
            StringBuilder builder = new StringBuilder("");
            builder.append("as").append(i).append("dfa").append(i+1).append("sdfaswesdgaeasdg").append(i+2).append("adgadfgafgafgafga")
                    .append(i+3).append("fgadfgafg");
            System.out.println( builder.toString());
        }
        System.out.println("use: "+ (System.currentTimeMillis() - start));
    }
}
