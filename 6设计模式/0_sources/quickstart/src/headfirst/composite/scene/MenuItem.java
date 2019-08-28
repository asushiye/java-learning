package headfirst.composite.scene;

import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment :  为菜单项提供空迭代器
 * @since : 2019/8/27
 */

public class MenuItem extends MenuComponent {
    private Object data;
    @Override
    public Object getData(){
        return data;
    }

    public MenuItem(Object data) {
        this.data = data;
    }

    @Override
    public void print(){
        System.out.println(data.toString());
    }

    @Override
    public Iterator createIterator() {
        return new NullIterator();
    }
}
