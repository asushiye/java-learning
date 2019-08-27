package headfirst.iterator.scene;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        startTest();

    }

    public static void startTest(){
        BreakfastMenu breakfastMenu = new BreakfastMenu();
        LunchMenu lunchMenu =  new LunchMenu();
        Waiter waiter = new Waiter(breakfastMenu, lunchMenu);
        waiter.printMenu();
    }
}
