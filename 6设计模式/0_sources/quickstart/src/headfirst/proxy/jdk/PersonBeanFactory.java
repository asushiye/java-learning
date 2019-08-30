package headfirst.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author : zhenyun.su
 * @comment :  代理工厂，返回PersonBean代理
 * @since : 2019/8/30
 */

public class PersonBeanFactory {

    //返回访问自己的代理
    public static PersonBean getOwnerProxy(PersonBean person){
        return (PersonBean) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person));
    }

    //返回访问其他人的代理
    public static PersonBean getNotOwnerProxy(PersonBean person){
        return (PersonBean) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NotOwnerInvocationHandler(person));
    }

    public static Boolean isProxyClass(PersonBean person){
        return Proxy.isProxyClass(person.getClass());
    }

}
