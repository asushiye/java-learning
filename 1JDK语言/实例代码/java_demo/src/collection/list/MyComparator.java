package collection.list;

import java.util.Comparator;

/**
 * @author : zhenyun.su
 * @comment :从小到大
 * @since : 2019-10-11
 */

public class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
