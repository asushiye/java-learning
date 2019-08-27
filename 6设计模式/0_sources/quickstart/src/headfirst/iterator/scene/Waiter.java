package headfirst.iterator.scene;

import java.util.ArrayList;

/**
 * @author : zhenyun.su
 * @comment : 服务员提供早餐和午餐的菜单
 * @since : 2019/8/27
 */

public class Waiter {
    private BreakfastMenu breakfastMenu;
    private LunchMenu lunchMenu;

    public Waiter(BreakfastMenu breakfastMenu, LunchMenu lunchMenu) {
        this.breakfastMenu = breakfastMenu;
        this.lunchMenu = lunchMenu;
    }

    public void printMenu(){
        System.out.println("print breakfast Menu");
        ArrayList<MenuItem> breakfastItems= breakfastMenu.getMenuItems();
        for (int i = 0; i <breakfastItems.size()-1; i++) {
            System.out.println(breakfastItems.get(i).toString());
        }

        System.out.println("print lunch Menu");
        MenuItem[] lunchItems = lunchMenu.getMenuItems();
        for (int i = 0; i < lunchItems.length-1; i++) {
            if (lunchItems[i] !=null){
                System.out.println(lunchItems[i].toString());
            }
        }
    }
}
