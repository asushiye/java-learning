package collection;

import java.util.*;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-10
 */

public class Test {
    public static void main(String[] args) {
//        init();
        printCollection();
    }

    public static void init() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        intList.set(0, 99);
        System.out.println(intList);
        intList.add(6);

        Collection<Integer> collections = new ArrayList<>(intList);
        collections.add(6);
        Integer[] ints = {7, 8, 9};
        collections.addAll(Arrays.asList(ints));
        System.out.println(collections);
    }

    public static void printCollection(){
        System.out.println((FillCollection.fill(new ArrayList<String>())));
        System.out.println((FillCollection.fill(new LinkedList<String>())));
        System.out.println((FillCollection.fill(new HashSet<String>())));
        System.out.println((FillCollection.fill(new TreeSet<String>())));
        System.out.println((FillCollection.fill(new LinkedHashSet<String>())));
        System.out.println((FillCollection.fill(new HashMap<String, String>())));
        System.out.println((FillCollection.fill(new TreeMap<String, String>())));
        System.out.println((FillCollection.fill(new LinkedHashMap<String, String>())));
    }


}
