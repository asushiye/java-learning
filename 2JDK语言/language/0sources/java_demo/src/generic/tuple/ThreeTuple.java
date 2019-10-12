package generic.tuple;

/**
 * @author : zhenyun.su
 * @comment : 3Î¬Ôª×æ
 * @since : 2019-10-12
 */

public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public final C three;

    public ThreeTuple(A first, B secord, C three) {
        super(first, secord);
        this.three = three;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "three=" + three +
                ", first=" + first +
                ", secord=" + secord +
                '}';
    }
}
