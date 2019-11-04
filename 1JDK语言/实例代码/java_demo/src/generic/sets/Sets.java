package generic.sets;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : zhenyun.su
 * @comment : 并集，交集，去除集合中其他集合元素，去除交集元素的两个集合元素
 * @since : 2019-10-21
 */

public class Sets {
    //并集
    public static <T> Set<T> union(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    //交集
    public static <T> Set<T> intersection(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }
    //去除集合中其他集合元素
    public static <T> Set<T> difference(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<T>(a);
        result.removeAll(b);
        return result;
    }
    //去除交集元素的两个集合元素
    public static <T> Set<T> complement(Set<T> a, Set<T> b){
        return difference(union(a, b), intersection(a, b));
    }
}
