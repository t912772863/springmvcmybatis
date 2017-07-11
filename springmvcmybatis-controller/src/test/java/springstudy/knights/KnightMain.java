package springstudy.knights;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/6/20 0020.
 */
public class KnightMain {
    public static void main(String[] args) {
        // 因为配置文件是xml格式的, 所以用xml上下文比较合适
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("knights.xml");
        // 获取加载的类
        BraveKnight knight = context.getBean(BraveKnight.class);
        knight.embarkOnQuest();


        context.close();
    }
}
