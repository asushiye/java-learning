package generic.method;

import java.util.*;

/**
 * @author : zhenyun.su
 * @comment : 创建集合对象工具
 * @since : 2019-10-18
 */

public final class CollectionNewUtils {
    public static <T> ArrayList<T> arrayList(){
        return new ArrayList<T>();
    }
    public static <T> LinkedList<T> linkedList(){
        return new LinkedList<T>();
    }
    public static <T> HashSet<T> hashSet(){
        return new HashSet<T>();
    }
    public static <T> TreeSet<T> treeSet(){
        return new TreeSet<T>();
    }
    public static <T> LinkedHashSet<T> linkedHashSet(){
        return new LinkedHashSet<T>();
    }
    public static <T> PriorityQueue<T> priorityQueue(){
        return new PriorityQueue<>();
    }
    public static <T, K> HashMap<T,K> hashMap(){
        return new HashMap<T, K>();
    }
    public static <T, K> TreeMap<T,K> treeMap(){
        return new TreeMap<T, K>();
    }
    public static <T, K> LinkedHashMap<T,K> linkedHashMap(){
        return new LinkedHashMap<T, K>();
    }
}
