package structure.adapter.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class ClassAdapter extends Adaptee implements Target {

    @Override
    public void request() {
        specificRequest();
    }
}
