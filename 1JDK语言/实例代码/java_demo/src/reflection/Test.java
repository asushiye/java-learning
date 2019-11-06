package reflection;

import reflection.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Collections;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */

public class Test {

    public static void main(String[] args) {
//        constructor();
//        method();
//        field();
//        annotation();
        generic();
    }

    public static void constructor() {
        Class<Animal> clazz = Animal.class;
        for (Constructor constructor : clazz.getConstructors()) {
            System.out.println(constructor);
            for (Class param : constructor.getParameterTypes()) {
                System.out.println(constructor.getName() + " param " + param);
            }
        }
        try {
            Constructor defaultCons = clazz.getConstructor();
            Animal animal1 = (Animal) defaultCons.newInstance();
            System.out.println(animal1);

            Constructor parmCons = clazz.getConstructor(String.class, Integer.class);
            Animal animal2 = (Animal) parmCons.newInstance("zhenyun", 30);
            System.out.println(animal2);

            Animal animal3 = clazz.newInstance();
            System.out.println(animal3);
        } catch (Exception e) {
            System.out.println("constructor Exception " + e.getMessage());
        }
    }

    public static void method() {
        Class<Animal> clazz = Animal.class;
        for (Method method : clazz.getMethods()) {
            if (method.getName().contains("toString") || method.getName().contains("print")) {
                System.out.println(method);
                for (Class<?> param : method.getParameterTypes()) {
                    System.out.println(param);
                }
            }
        }
        try {
            Animal animal = clazz.newInstance();
            Method toString = clazz.getMethod("toString");
            String str = (String) toString.invoke(animal);
            System.out.println(str);

            Method print = clazz.getMethod("print", String.class, Integer.class);
            Boolean isSuccess = (Boolean) print.invoke(animal, "zhenyun.su", 1000);
            System.out.println(isSuccess);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void field() {
        Class<Animal> clazz = Animal.class;
        System.out.println("clazz.getDeclaredFields()");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field);
        }
        System.out.println("clazz.getFields()");
        for (Field field : clazz.getFields()) {
            System.out.println(field);
        }

        try {
            Animal animal = clazz.getConstructor(String.class, Integer.class)
                    .newInstance("zhenyun.su", 1000);
            System.out.println(animal);
            Field name = clazz.getDeclaredField("name");
            name.setAccessible(true);
            System.out.println(name.get(animal));
            name.set(animal, "asushiye");
            System.out.println(animal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printAnnotation(Annotation[] annotations){
        for (Annotation annotation: annotations){
            System.out.println(annotation);
            if (annotation instanceof MyTypeAnnotation){
                String name = ((MyTypeAnnotation) annotation).name();
                String value = ((MyTypeAnnotation) annotation).value();
                System.out.println("name: "+name+"; value: "+value);
            };
            if (annotation instanceof MyFieldAnnotation){
                String name = ((MyFieldAnnotation) annotation).name();
                String value = ((MyFieldAnnotation) annotation).value();
                System.out.println("name: "+name+"; value: "+value);
            };
            if (annotation instanceof MyMethodAnnotation){
                String name = ((MyMethodAnnotation) annotation).name();
                String value = ((MyMethodAnnotation) annotation).value();
                System.out.println("name: "+name+"; value: "+value);
            };
            if (annotation instanceof MyParameterAnnotation){
                String name = ((MyParameterAnnotation) annotation).name();
                String value = ((MyParameterAnnotation) annotation).value();
                System.out.println("name: "+name+"; value: "+value);
            };
        }
    }
    public static void annotation() {
        Class<AnimalAnnotation> clazz = AnimalAnnotation.class;
        printAnnotation(clazz.getAnnotations());

        try{
            Field name = clazz.getDeclaredField("name");
            printAnnotation(name.getAnnotations());

            Method print = clazz.getMethod("print", String.class, Integer.class);
            printAnnotation(print.getAnnotations());

            for(Annotation[] annotations: print.getParameterAnnotations()){
                printAnnotation(annotations);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void printGeneric(Type generiType){ ;
        System.out.println("type: "+ generiType);
        if (generiType instanceof ParameterizedType){
            ParameterizedType pType = (ParameterizedType)generiType;
            for(Type aType: pType.getActualTypeArguments()){
                System.out.println("generi type: "+aType);
            }
        }
    }

    public static void generic() {
        Class<Animal> clazz = Animal.class;
        try{
            System.out.println("1 Field.getGenericType---------------");
            Field members = clazz.getDeclaredField("members");
            System.out.println("field: "+members);
//            printGeneric(members.getType());
            printGeneric(members.getGenericType());

            System.out.println("2 Method.getGenericReturnType---------------");
            Method getMembers = clazz.getMethod("getMembers");
            System.out.println("method: "+getMembers);
//            printGeneric(getMembers.getReturnType());
            printGeneric(getMembers.getGenericReturnType());

            System.out.println("3 Method.getGenericParameterTypes---------------");
            Method setMembers = clazz.getMethod("setMembers", List.class);
            System.out.println("method: "+ setMembers);
//            System.out.println(setMembers.getParameterTypes());
            for(Type paramType: setMembers.getGenericParameterTypes()){
                printGeneric(paramType);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
