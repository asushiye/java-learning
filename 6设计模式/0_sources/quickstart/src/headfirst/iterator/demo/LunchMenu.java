package headfirst.iterator.demo;

import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment : 提供牛肉饭，牛肉面，土豆饭的午餐店
 * @since : 2019/8/27
 */

public class LunchMenu implements Menu  {
    private MenuItem[] menuItems;
    private int Max_Length = 6;
    private int index=0;

    public LunchMenu() {
        this.menuItems = new MenuItem[6];
        addMenu("Beef Rice", "Beef Rice", false, 11.0f);
        addMenu("beef noodle", "beef noodle", false, 12.5f);
        addMenu("Potato rice", "Potato rice", true, 9.0f);
    }

    public void addMenu(String name, String desc, Boolean vege, float price){
        menuItems[index]=new MenuItem(name, desc, vege, price);
        index=index+1;
    }

    @Override
    public Iterator createIterator() {
        return new LunchMenuIterator(menuItems);
    }
}
