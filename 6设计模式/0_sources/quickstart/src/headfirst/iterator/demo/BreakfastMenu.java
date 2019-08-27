package headfirst.iterator.demo;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment : 面条，牛奶，热狗，肉包的早餐店
 * @since : 2019/8/27
 */

public class BreakfastMenu implements Menu {
    private ArrayList<MenuItem> menuItems= new ArrayList<>();

    public BreakfastMenu() {
        addMenu("noodle", "Onion oil noodles", true, 6.0f);
        addMenu("milk", "Pure milk", false, 3.5f);
        addMenu("Hot dog", "Hot dog", true, 5.0f);
        addMenu("Meat bag", "Meat bag", true, 2.0f);
    }

    public void addMenu(String name, String desc, Boolean vege, float price){
        menuItems.add(new MenuItem(name, desc, vege, price));
    }

    @Override
    public Iterator createIterator() {
        return menuItems.iterator();
    }
}
