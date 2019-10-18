package generic.inter;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-18
 */

public abstract class Coffee {
    private static long counter = 0;
    private final long id=counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName()+" "+id;
    }
}
