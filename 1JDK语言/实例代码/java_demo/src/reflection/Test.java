package reflection;

import java.lang.reflect.Constructor;
import java.util.Collections;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */

public class Test {

    public static void main(String[] args) {
        constructor();
    }

    public static void constructor() {
        Class clazz = Animal.class;
        for (Constructor constructor: clazz.getConstructors()){
            System.out.println(constructor);
            for(Class param: constructor.getParameterTypes()){
                System.out.println(constructor.getName()+" param "+param);
            }
        }
        try{
            Constructor defaultCons = clazz.getConstructor();
            Animal animal1 = (Animal)defaultCons.newInstance();
            System.out.println(animal1);

            Constructor parmCons = clazz.getConstructor(String.class);
            Animal animal2 = (Animal)parmCons.newInstance("zhenyun");
            System.out.println(animal2);

            Constructor parmCons2 = clazz.getConstructor(String.class, Integer.class);
            Animal animal3 = (Animal)parmCons.newInstance("zhenyun", 30);
            System.out.println(animal3);
        }catch(Exception e){
            System.out.println("constructor Exception "+e.getMessage());
        }

    }
}
