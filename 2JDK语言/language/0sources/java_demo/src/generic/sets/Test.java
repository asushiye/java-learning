package generic.sets;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-21
 */

public class Test {
    public static void main(String[] args) {
        Set<Integer> a = new HashSet<Integer>(Arrays.asList(1,2,3));
        Set<Integer> b = new HashSet<Integer>(Arrays.asList(3,4,5));
        System.out.println("a: "+ a+"; b: "+ b);
        System.out.println("Sets.union(a, b): "+ Sets.union(a, b));
        System.out.println("Sets.intersection(a, b): "+ Sets.intersection(a, b));
        System.out.println("Sets.difference(a, b): "+ Sets.difference(a, b));
        System.out.println("Sets.complement(a, b): "+ Sets.complement(a, b));
    }
}
