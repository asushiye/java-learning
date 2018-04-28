import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LdhInvocationHandler implements InvocationHandler {
    private Person person = null;

    public LdhInvocationHandler(Person person) {
        this.person = person;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sing".equals(method.getName())){
            System.out.println("我是他的经纪人，要找他唱歌，先交10万");
            return method.invoke(person,args);
        }
        if ("dance".equals(method.getName())){
            System.out.println("我是他的经纪人，要找他跳舞，先交20万");
            return method.invoke(person,args);
        }
        return null;
    }
}
