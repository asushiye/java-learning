package generic.tuple;

/**
 * @author : zhenyun.su
 * @comment : Ôª×æ¹¤¾ß
 * @since : 2019-10-21
 */

public class Tuple {
    public static <A, B> TwoTuple<A, B> tuple(A a, B b){
        return new TwoTuple<A, B>(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c){
        return new ThreeTuple<A, B, C>(a, b, c);
    }

    public static <A, B, C,D> FourTuple<A, B, C,D> tuple(A a, B b, C c, D d){
        return new FourTuple<A, B, C, D>(a, b, c, d);
    }
}
