package reflection.proxy;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-06
 */

public class LiuDeHuaSimpleProxy implements Person {
    private Person person;

    public LiuDeHuaSimpleProxy(Person person) {
        this.person = person;
    }
    public static Person getPersonProxy(Person person){
        return new LiuDeHuaSimpleProxy(person);
    }

    @Override
    public String sing(String name) {
        System.out.println("我是他的经纪人，要找他唱歌，先交10万");
        return person.sing(name);
    }

    @Override
    public String dance(String name) {
        System.out.println("我是他的经纪人，要找他跳舞，先交20万");
        return person.dance(name);
    }
}
