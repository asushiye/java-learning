package statics;

import object.MyObject;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/19
 */

public class Test {
    public static void main(String[] args) {
        double a1 = Math.round(42.2);
        System.out.println("Math.round(42.2)= "+a1);
        double a2 = Math.ceil(42.2);
        System.out.println("Math.ceil(42.2)= "+a2);
        double a3 = Math.floor(42.2);
        System.out.println("Math.floor(42.2)= "+a3);

        MyStatic myStatic = new MyStatic();
        System.out.println(myStatic.getNotStaticObject().getName());
        System.out.println(myStatic.getStaticObject().getName());

        MyStatic.NotStaticObject notStaticObject1 = myStatic.new NotStaticObject("new MyStatic.NotStaticObject");
        System.out.println(notStaticObject1.getName());

        MyStatic.StaticObject staticObject1 = new MyStatic.StaticObject("new MyStatic.StaticObject");
        System.out.println(staticObject1.getName());

        System.out.println(Duck.duckCount);
        Duck duck = new Duck();
        Duck.print();
    }
}
