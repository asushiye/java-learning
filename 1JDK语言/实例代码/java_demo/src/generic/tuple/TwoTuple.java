package generic.tuple;

/**
 * @author : zhenyun.su
 * @comment : 2Î¬Ôª×æ
 * @since : 2019-10-12
 */

public class TwoTuple<A, B> {
    public final A first;
    public final B secord;

    public TwoTuple(A first, B secord) {
        this.first = first;
        this.secord = secord;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "first=" + first +
                ", secord=" + secord +
                '}';
    }
}
