package headfirst.composite.scene;

import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment : 空迭代器
 * @since : 2019/8/27
 */

public class NullIterator implements Iterator {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
