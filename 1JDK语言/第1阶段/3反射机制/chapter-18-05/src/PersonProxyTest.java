public class PersonProxyTest {
    public static void main(String[] args) {
        LiuDeHuaProxy proxy = new LiuDeHuaProxy();

        Person person= proxy.getPersonProxy();
        String retSing = person.sing("冰雨");
        System.out.println(retSing);
        String retDance = person.dance("街舞");
        System.out.println(retDance);
    }
}
