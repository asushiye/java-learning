package headfirst.composite.scene;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/27
 */

public class CompositeIterator implements Iterator {
    private Stack stack= new Stack();

    public CompositeIterator(Iterator iterator) {
        this.stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()){
            return false;
        }else{
            Iterator iterator = (Iterator) stack.peek();
            if (!iterator.hasNext()){
                stack.pop();
                return hasNext();
            }else{
                return true;
            }
        }
    }

    @Override
    public Object next() {
        if (hasNext()) {
            Iterator iterator = (Iterator) stack.peek();
            MenuComponent menuComponent = (MenuComponent)iterator.next();
            if (menuComponent instanceof Menu){
                stack.push(menuComponent.createIterator());
            }
            return menuComponent;
        }
        return null;
    }

    @Override
    public void remove() {
        throw  new UnsupportedOperationException();
    }
}
