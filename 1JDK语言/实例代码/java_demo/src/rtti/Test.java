package rtti;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-04
 */

public class Test {
        public static void main(String[] args) {
            System.out.println("before new Candy()");
//      newCandy();
//        forName();
//        getclass();
//            getId();
//            getRtti();
//            getSuperRtti();
            getCastRttiDD();
            System.out.println("after new Candy()");
        }

        public static void newCandy() {
            new Candy();
        }

    public static void forName() {
        try{
            Class clazz = Class.forName("rtti.Candy");
        }catch(ClassNotFoundException e){
            System.out.println("Candy ClassNotFoundException");
        }
    }
    public static void getclass() {
        Class clazz= Candy.class;
        System.out.println(clazz.getName());
    }

    public static void getId() {
        System.out.println(Candy.id);
//        System.out.println(Candy.count);
//        Candy.print();
    }


    public static void getRtti() {
        Class clazz = Candy.class;
        System.out.println("Class clazz = Candy.class");
        System.out.println("clazz.getName(): "+clazz.getName());
        System.out.println("clazz.getSimpleName(): "+clazz.getSimpleName());
        System.out.println("clazz.toString(): "+clazz.toString());
        System.out.println("clazz.getSuperclass(): "+clazz.getSuperclass());
        for (Class inter: clazz.getInterfaces()){
            System.out.println("Interface: "+inter.getName());
        }
        for (Constructor con: clazz.getConstructors()){
            System.out.println("Constructor: "+con.getName());
        }

        for(Field field: clazz.getDeclaredFields()){
            System.out.println("Field: "+field.getName());
        }
        for(Method method: clazz.getMethods()){
            System.out.println("Method: "+method.getName());
        }
    }

    public static void getSuperRtti() {
        System.out.println("Class clazz = Candy.class");
        Class clazz = Candy.class;
        Class<SuperCandy> superClazz = clazz.getSuperclass();
        try{
            SuperCandy candy = superClazz.newInstance();
            candy.call();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void getRttiDD() {
        System.out.println("Class clazz = Candy.class");
        Class<Candy> clazz = Candy.class;
        Class<? super Candy> clazzSuper = clazz.getSuperclass();
        try{
            SuperCandy candy = (SuperCandy)clazzSuper.newInstance();
            candy.call();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void getCastRttiDD() {
        System.out.println("SuperCandy superCandy = new Candy()");
        SuperCandy superCandy = new Candy();
        Class<Candy> clazz = Candy.class;
        Candy candy = clazz.cast(superCandy);
        candy.swim();
    }
}
