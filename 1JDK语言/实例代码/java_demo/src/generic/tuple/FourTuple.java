package generic.tuple;

/**
 * @author : zhenyun.su
 * @comment : 4Î¬Ôª×æ
 * @since : 2019-10-12
 */

public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
    public final D four;

    public FourTuple(A first, B secord, C three, D four) {
        super(first, secord, three);
        this.four = four;
    }

    @Override
    public String toString() {
        return "FourTuple{" +
                "four=" + four +
                ", three=" + three +
                ", first=" + first +
                ", secord=" + secord +
                '}';
    }
}
