package generic.tuple;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-12
 */

public class Test {
    public static void main(String[] args) {
        TwoTuple<String, Integer> twoTuple = new TwoTuple<>("zhenyun.su", 25);

        ThreeTuple<Integer, String, Long> threeTuple = new ThreeTuple<>(24, "hello", 23L);

        FourTuple<Integer, String, Long, Double> fourTuple = new FourTuple<>(24, "hello", 23L, 3.3);
        System.out.println("TwoTuple:" + twoTuple);
        System.out.println("ThreeTuple:" + threeTuple);
        System.out.println("FourTuple:" + fourTuple);
    }
}
