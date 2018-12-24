package Collections;

import java.util.Collection;
import java.util.Iterator;

public class MyCollectionUtil {

    public static void printlnItem(Collection collection){
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    public static void addItem(Collection collection, Object o){
        collection.add(o);
    }

    public static void clearItem(Collection collection){
        collection.clear();
    }

    public static void remoteItem(Collection collection, Object o){
        collection.remove(o);
    }
}
