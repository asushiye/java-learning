package headfirst.composite.scene;

import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/27
 */

public class Waitress {
    MenuComponent allMenus;

    public Waitress(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }
    public void printMenu(){
        allMenus.print();
    }

    public void printIterator(){
        allMenus.print();
        Iterator iterator = allMenus.createIterator();
        while (iterator.hasNext()){
            MenuComponent menuComponent = (MenuComponent)iterator.next();
            menuComponent.print();
        }
    }
}
