package concurrent.resource;

import java.util.Random;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        protected synchronized Integer initialValue(){
            return 0;
        }
    };

    public static int get(){
        return value.get();
    }

    public static void increment(){
        value.set(value.get()+1);
    }
}
