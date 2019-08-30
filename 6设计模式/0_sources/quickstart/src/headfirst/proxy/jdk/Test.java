package headfirst.proxy.jdk;

/**
 * @author : zhenyun.su
 * @comment : 代理模式 测试
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        // 类适配器
        startTest();
    }

    public static void startTest(){
        PersonBean person1 = new PersonBeanImpl();
        person1.setGender("女");
        person1.setName("lili");
        person1.setHotOrNotRating(8);
        PersonBean owner = PersonBeanFactory.getOwnerProxy(person1);
        System.out.println(owner.getName()+" is "+owner.getGender()+"; 分数："+owner.getHotOrNotRating());
        try{
            owner.setGender("男");
            System.out.println(owner.getName()+" is "+owner.getGender()+"; 分数："+owner.getHotOrNotRating());
            owner.setHotOrNotRating(6);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        PersonBean other = PersonBeanFactory.getNotOwnerProxy(person1);

        System.out.println(other.getName()+" is "+other.getGender()+"; 分数："+other.getHotOrNotRating());
        try{
            other.setGender("男");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        other.setHotOrNotRating(6);
        System.out.println(other.getName()+" is "+other.getGender()+"; 分数："+other.getHotOrNotRating());

        if (PersonBeanFactory.isProxyClass(other)){
            System.out.println("other is proxy class");
        }else{
            System.out.println("other is not proxy class");
        }

    }
}
