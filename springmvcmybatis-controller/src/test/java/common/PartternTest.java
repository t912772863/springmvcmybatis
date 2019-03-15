package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试正则表达式用法
 * Created by tianxiong on 2019/3/15.
 */
public class PartternTest {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // 要匹配的目标字符串
        String rslt = "AA_#A301----BQQQQQ_!B302";
        // 用Pattern的静态方法,传入一个正则表达式. 注意这里的正则是没有以^开头, 没有以$结尾的, 表示匹配字符串的任何位置, 因此可能匹配多个.
        // ^ 匹配开砂  $ 匹配结尾   ^$同时存在全匹配
        Matcher m = Pattern.compile("([A-Z]+_)[~!！@#￥…&*（） ^:;\"'\\[\\]\\-]+[A-Z]+[0-9]+").matcher(rslt);
        // 由于匹配任意位置, 可能匹配多个, 所以匹配到一个打印一个
        while (m.find()){
            // m.group方法, 无参表示获取匹配到的字符串
            System.out.println(m.group());
            // 有参的情况下, 传入0, 和无参的结果是一样的
            System.out.println(m.group(0));
            // 当传入大于0的其它数字时, 表示获取匹配到的字符串, 对应正则表达式中的()所匹配到的内容. 注意上面正则中的小括号是正则语法不需要的,是为了取值方便,还可以加入多个
            System.out.println(m.group(1));
        }

    }
}
