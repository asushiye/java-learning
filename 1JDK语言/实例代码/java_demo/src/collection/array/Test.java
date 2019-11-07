package collection.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-07
 */

public class Test {
    public static void main(String[] args) {
//        base();
//        print();
//        testArrays();
        arrayCopy();
    }
    public static void base() {
        Integer[] int1 = new Integer[3];
        int[] int2 = new int[3];
        Integer[] int3 = {0, 1 , 2};
        int1[1] = 3;
        int2[1] = 3;
        System.out.println(int1.length);
        System.out.println(Arrays.toString(int1));
        System.out.println(Arrays.toString(int2));
        System.out.println(Arrays.toString(int3));
    }
    public static void print() {
        Integer[] int3 = {0, 1 , 2};
        for(Integer i:int3){
            System.out.println(i);
        };
        for (int i = 0; i < int3.length; i++) {
            System.out.println(int3[i]);
        }
    }

    public static void testArrays(){
        Integer[] int3 = {0, 2 , 1};
        Arrays.fill(int3, 1, 2, 5);
        System.out.println(Arrays.toString(int3));
        Integer[] int4 = {0, 5 , 1};
        System.out.println(Arrays.toString(int4));
        System.out.println(Arrays.equals(int3, int4));

        Arrays.sort(int4);
        Arrays.sort(int3, 0, int3.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1>o2 ? -1: o1<o2 ? 1: 0;
            }
        });
        System.out.println(Arrays.toString(int4));
        System.out.println(Arrays.toString(int3));

        System.out.println(Arrays.binarySearch(int4, 5));
        System.out.println(Arrays.binarySearch(int3, 5));
        System.out.println(Arrays.hashCode(int3));
        System.out.println(Arrays.hashCode(int4));
        List<Integer> list = Arrays.asList(int3);
        System.out.println(list);
    }

    public static void arrayCopy(){
        Integer[] int3 = {0, 2 , 1, 3};
        Integer[] int4 = new Integer[5];
        System.out.println(Arrays.toString(int3));
        System.out.println(Arrays.toString(int4));
        System.arraycopy(int3, 1, int4, 4, int3.length-1);
        System.out.println(Arrays.toString(int4));
    }
}
