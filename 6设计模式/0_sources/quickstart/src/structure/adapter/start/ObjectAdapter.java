package structure.adapter.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class ObjectAdapter implements Target {

    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    @Override
    public void request() {
        this.adaptee.specificRequest();
    }
}
