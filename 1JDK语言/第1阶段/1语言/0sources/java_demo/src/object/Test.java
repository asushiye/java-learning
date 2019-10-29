package object;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/17
 */

public class Test {
    public static void main(String[] args) {

        MyObject myObject1 = new MyObject("A","1");
        System.out.println("myObject1.hashCode="+myObject1.hashCode());
        MyObject myObject2 = new MyObject("A","2");
        System.out.println("myObject2.hashCode="+myObject2.hashCode());
        MyObject myObject3= myObject1;
        System.out.println("myObject3.hashCode="+myObject3.hashCode());

        System.out.println("myObject1==myObject3="+(myObject1==myObject3));
        System.out.println("myObject1.equals(myObject3)="+myObject1.equals(myObject3));


        System.out.println("myObject1==myObject2="+(myObject1==myObject2));
        System.out.println("myObject1.equals(myObject2)="+myObject1.equals(myObject2));

        myObject2.setKey("22");
        System.out.println("myObject1.equals(myObject2)="+myObject1.equals(myObject2));

        Duck duck = new Duck();
    }
}
