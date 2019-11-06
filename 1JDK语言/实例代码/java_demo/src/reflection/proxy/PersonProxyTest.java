package reflection.proxy;

public class PersonProxyTest {
    public static void main(String[] args) {
//        simpleProxy();
        dynamicProxy();
    }
    public static void simpleProxy() {
//        Person person = new LiuDeHua();
        Person person = LiuDeHuaSimpleProxy.getPersonProxy(new LiuDeHua());
        System.out.println(person.sing("冰雨"));
        System.out.println(person.dance("街舞"));
    }
    public static void dynamicProxy() {
        Person person= LiuDeHuaProxy.getPersonProxy(new LiuDeHua());

        String retSing = person.sing("冰雨");
        System.out.println(retSing);
        String retDance = person.dance("街舞");
        System.out.println(retDance);
    }
}
