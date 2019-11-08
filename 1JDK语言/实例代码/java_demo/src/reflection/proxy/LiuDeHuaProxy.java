package reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LiuDeHuaProxy {
    public static Person getPersonProxy(Person person){
        InvocationHandler ldhInvocationHandler = new LdhInvocationHandler(person);
        Class clazz = person.getClass();
            try {
                return(Person) Proxy.newProxyInstance(
                        clazz.getClassLoader(),
                        clazz.getInterfaces(),
                        ldhInvocationHandler
                );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
