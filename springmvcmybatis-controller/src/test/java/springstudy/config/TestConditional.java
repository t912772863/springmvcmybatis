package springstudy.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 在条件注入一些Bean的时候,用来判断条件是否成立的一个类
 * Created by Administrator on 2017/6/21 0021.
 */
public class TestConditional implements Condition {
    /**
     * 该方法返回true,则在对应@Conditional注解的Bean会注入.否则会忽略
     * @param context
     * @param metadata
     * @return
     */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return env.containsProperty("testBean");
    }
}
