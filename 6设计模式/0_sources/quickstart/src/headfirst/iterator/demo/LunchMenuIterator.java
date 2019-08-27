package headfirst.iterator.demo;

import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment : 午餐迭代器
 * @since : 2019/8/27
 */

public class LunchMenuIterator implements Iterator {
    private MenuItem[] menuItems;
    private int index=0;

    public LunchMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        return (index < menuItems.length)&&(menuItems[index]!=null);
    }

    @Override
    public Object next() {
        index= index+1;
        return menuItems[index-1];
    }

    @Override
    public void remove() {
        if (this.hasNext()){
            int removeIndex=index;
            for (int i = index; i < menuItems.length-1; i++) {
                menuItems[i]=menuItems[i+1];
                removeIndex=removeIndex+1;
            }
            menuItems[removeIndex]=null;
        }
    }
}
