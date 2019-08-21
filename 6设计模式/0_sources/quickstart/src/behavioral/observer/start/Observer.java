package behavioral.observer.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/14
 */

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
