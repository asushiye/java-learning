package reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LiuDeHuaProxy {
    public static Person getPersonProxy(Person person){
        InvocationHandler ldhInvocationHandler = new LdhInvocationHandler(person);
        try {
            return(Person) Proxy.newProxyInstance(
                    LiuDeHuaProxy.class.getClassLoader(),
                    person.getClass().getInterfaces(),
                    ldhInvocationHandler
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
