package headfirst.iterator.demo;

import java.util.Iterator;

/**
 * @author : zhenyun.su
 * @comment : 服务员提供早餐和午餐的菜单
 * @since : 2019/8/27
 */

public class Waiter {
    private Menu breakfastMenu;
    private Menu lunchMenu;

    public Waiter(Menu breakfastMenu, Menu lunchMenu) {
        this.breakfastMenu = breakfastMenu;
        this.lunchMenu = lunchMenu;
    }

    public void printMenu(){
        System.out.println("print breakfast Menu");
        Iterator breakfastIterator = breakfastMenu.createIterator();
        printMenu(breakfastIterator);

        System.out.println("print lunch Menu");
        Iterator lunchIterator = lunchMenu.createIterator();
        printMenu(lunchIterator);
    }

    public void printMenu(Iterator iterator){
        while(iterator.hasNext()){
            MenuItem menuItem = (MenuItem)iterator.next();
            System.out.println(menuItem.toString());
        }
    }
}
