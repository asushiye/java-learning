package headfirst.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author : zhenyun.su
 * @comment : 访问自己时，不能修改评分
 * @since : 2019/8/30
 */

public class OwnerInvocationHandler implements InvocationHandler {
    private PersonBean personBean;

    public OwnerInvocationHandler(PersonBean personBean) {
        this.personBean = personBean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.getName().startsWith("get")) {
                return method.invoke(personBean, args);
            } else if (method.getName().equals("setHotOrNotRating")) {
                throw  new IllegalAccessException();
            } else if (method.getName().startsWith("set")) {
                return method.invoke(personBean, args);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
