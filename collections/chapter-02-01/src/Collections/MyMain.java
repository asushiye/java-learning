package Collections;

import java.util.*;

public class MyMain {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("zhenyun.su");

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "asu1");
        map.put(2, "asu2");

        MyCollectionUtil.clearItem(list);
        MyCollectionUtil.addItem(list, "asushiye");
        MyCollectionUtil.printlnItem(list);

        MyCollectionUtil.remoteItem(set, 2);
        MyCollectionUtil.printlnItem(set);
        MyCollectionUtil.printlnItem(map.entrySet());

        Collection collection = new HashSet();
        collection.addAll(set);
        MyCollectionUtil.printlnItem(collection);

        System.out.println(collection.contains(1));
        collection.removeAll(set);
        System.out.println(collection.contains(1));
        int numberOfElements = collection.size();



    }
}
