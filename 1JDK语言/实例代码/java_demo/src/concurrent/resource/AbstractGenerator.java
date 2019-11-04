package concurrent.resource;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public abstract class AbstractGenerator<T> {
    private volatile  boolean cancel = false;
    public void cancel(){
        this.cancel = true;
    }
    public boolean isCancel(){
        return this.cancel;
    }

    public abstract T next();
}
