package headfirst.composite.scene;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/27
 */

public class Menu extends MenuComponent{
    private ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    private Object data;

    public Menu(Object data) {
        this.data = data;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponents.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menuComponents.get(i);
    }

    @Override
    public void print() {
        System.out.println("-------------");
        System.out.println(data.toString());
//        Iterator iterator = menuComponents.iterator();
//        while (iterator.hasNext()){
//           MenuComponent menuComponent= (MenuComponent)iterator.next();
//           menuComponent.print();
//        }
    }

    @Override
    public Iterator createIterator() {
        return new CompositeIterator(menuComponents.iterator());
    }
}
