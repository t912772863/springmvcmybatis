package springstudy.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 *
 * Created by Administrator on 2017/6/26 0026.
 */
@Aspect
public class EncoreableIntroducer {
    @DeclareParents(value="springstudy.aop.Performance+", defaultImpl=DefaultEncoreable.class)
    public static Encoreable encoreable;
}
