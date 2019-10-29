package concurrent.resource;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public class IntGenerator extends AbstractGenerator<Integer> {
    private Integer count = 0;
    @Override
    public synchronized Integer next() {
        ++count;
//        Thread.yield();
        ++count;
        return count;
    }
}
