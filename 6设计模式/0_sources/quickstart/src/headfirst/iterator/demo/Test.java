package headfirst.iterator.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        Menu breakfastMenu = new BreakfastMenu();
        Menu lunchMenu =  new LunchMenu();
        Waiter waiter = new Waiter(breakfastMenu, lunchMenu);
        waiter.printMenu();
    }

    public static void startTest(){
        Menu breakfastMenu = new BreakfastMenu();
        Menu lunchMenu =  new LunchMenu();
        Waiter waiter = new Waiter(breakfastMenu, lunchMenu);
        waiter.printMenu();
    }
}
