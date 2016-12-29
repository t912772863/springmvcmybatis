package designpattern.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 非拥有者本身调用代理
 * Created by Administrator on 2016/12/29 0029.
 */
public class NotOwnerInvocationHadler implements InvocationHandler {
    PersonBean personBean;

    public NotOwnerInvocationHadler(PersonBean personBean) {
        this.personBean = personBean;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            if(method.getName().startsWith("get")){
                return method.invoke(personBean,args);
            }else if(method.getName().equals("setHotOrRating")){
                return method.invoke(personBean,args);
            }else if(method.getName().startsWith("set")){
                throw new IllegalAccessException();
            }
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
        return null;
    }
}
