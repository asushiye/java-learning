package headfirst.composite.scene;

import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/27
 */

public abstract class MenuComponent {
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException("can not add MenuComponent");
    }
    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException("can not remove MenuComponent");
    }
    public MenuComponent getChild(int i){
        throw new UnsupportedOperationException("can not get child MenuComponent");
    }

    public Object getData(){
        throw new UnsupportedOperationException("can not get Menu Data");
    }

    public void print(){
        throw new UnsupportedOperationException("can not print Menu Data");
    }
    public Iterator createIterator(){
        throw new UnsupportedOperationException("can not get iterator");
    }
}
