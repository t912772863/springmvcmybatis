package designpattern.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 拥有者的代理
 * Created by Administrator on 2016/12/29 0029.
 */
public class OwnerInvocationHandler implements InvocationHandler {
    PersonBean personBean;

    public OwnerInvocationHandler(PersonBean personBean) {
        this.personBean = personBean;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try{
            // 如果是一个get方法就允许调用
            if(method.getName().startsWith("get")){
                return method.invoke(personBean,args);
            // 如果是设置关注度,本身不能给自己设置
            }else if(method.getName().equals("setHotOrRating")){
                throw new IllegalAccessException();
                // 其它个人信息都可以自己设定
            }else if(method.getName().startsWith("set")){
                return method.invoke(personBean,args);
            }
        }catch (InvocationTargetException e){
            // 如果真正的主题拋出异常,就会执行这里
            e.printStackTrace();
        }
        // 对其它方法的调用一律不理会
        return null;
    }
}
