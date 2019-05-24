import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LiuDeHuaProxy {
    private Person ldh = new LiuDeHua();

    public Person getPersonProxy(){
        InvocationHandler ldhInvocationHandler = new LdhInvocationHandler(ldh);
        try {
            return(Person) Proxy.newProxyInstance(LiuDeHuaProxy.class.getClassLoader(), ldh.getClass().getInterfaces(),
                    ldhInvocationHandler);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
