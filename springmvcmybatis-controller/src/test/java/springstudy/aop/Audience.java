package springstudy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * spring对aop的支持相关学习
 *
 * @Aspect 该注解把一个类标识为一个切面类.该注解是spring对Aspect的支持
 *
 * @After 通知方法会在目标方法返回或者拋出异常后调用.
 * @AfterReturning 通知方法会在目标方法返回后调用.
 * @AfterThrowing 通知方法会在目标方法拋出异常后调用.
 * @Around 通知方法会将目标方法封装起来, 也就是在目标方法调用的前后都会调用.
 * @Before 通知方法会在目标方法调用前调用.
 * @Pointcut 该注解可以在@Aspect切面内定义一个可重用的切点.需要重用的地方,直接引用方法名就可以了.
 *              可以该上一个表达式写多次的问题.
 * @DeclareParents
 *
 *
 *
 * Created by Administrator on 2017/6/22 0022.
 */
@Aspect
public class Audience {
    @Pointcut("execution(* springstudy.knights.BraveKnight*.*(..))")
    public void performance(){}

    /**
     * 方法调用前
     *
     */
    @Before("performance()")
    public void silenceCellPhone(){
        System.out.println("观众把手机设成静音");
    }
    @Before("performance()")
    public void takeSeats(){
        System.out.println("观众都就座.");
    }

    /**
     * 方法返回以后.
     */
    @AfterReturning("performance()")
    public void applause(){
        System.out.println("表演成功,观众鼓掌.");
    }

    /**
     * 方法拋出异常以后.
     */
    @AfterThrowing("performance()")
    public void demandRefund(){
        System.out.println("表演失败了, 观众要求退款");
    }

    /**
     * 环绕通知, 能要方法的各个环节进行织入.
     */
    @Around("performance()")
    public void around(ProceedingJoinPoint jp){
        try{
            // 调用方法前
            System.out.println("手机调成静音");

            // 把调用权限给拦截的方法本身
            jp.proceed();
            // 调用方法后
            System.out.println("演出成功,鼓掌");
        }catch (Throwable e){
            // 方法拋出异常后.
            System.out.println("演出失败了,快退钱.");
        }
    }
}
