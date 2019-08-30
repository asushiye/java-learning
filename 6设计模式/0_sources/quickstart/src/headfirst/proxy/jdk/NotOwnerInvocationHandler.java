package headfirst.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : zhenyun.su
 * @comment : 访问其他人时，能获取信息，不能修改
 * @since : 2019/8/30
 */

public class NotOwnerInvocationHandler implements InvocationHandler {
    private PersonBean personBean;

    public NotOwnerInvocationHandler(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
            } else if (method.getName().equals("setHotOrNotRating")) {
                return method.invoke(personBean, args);
            } else if (method.getName().startsWith("set")) {
                throw  new IllegalAccessException();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
