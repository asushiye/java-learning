package generic.method;

import java.util.Arrays;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment : 可变类型与泛型方法关系
 * @since : 2019-10-18
 */

public class GenericVarargs {
    public static <T> List<T> makeList(T...args){
        List<T> result= Arrays.asList(args);
        return result;
    }
}
