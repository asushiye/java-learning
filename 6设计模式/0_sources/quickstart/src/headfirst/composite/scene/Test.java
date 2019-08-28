package headfirst.composite.scene;

/**
 * @author : zhenyun.su
 * @comment : 组合模式 测试
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        // 类适配器
        startTest();

    }

    public static void startTest(){
        //早餐菜单
        MenuComponent breakfastMenu = new Menu(new MenuData("breakfastMenu","breakfast Menu"));
        breakfastMenu.add(new MenuItem(new MenuItemData("noodle", "Onion oil noodles", true, 6.0f)));
        breakfastMenu.add(new MenuItem(new MenuItemData("milk", "Pure milk", false, 3.5f)));
        breakfastMenu.add(new MenuItem(new MenuItemData("Hot dog", "Hot dog", true, 5.0f)));
        breakfastMenu.add(new MenuItem(new MenuItemData("Meat bag", "Meat bag", true, 2.0f)));
        //咖啡子菜单
        MenuComponent coffeeSubMenu = new Menu(new MenuData("coffeeSubMenu","lunch coffee subMenu"));
        coffeeSubMenu.add(new MenuItem(new MenuItemData("Mocha Coffee", "Mocha Coffee", false, 25.0f)));
        coffeeSubMenu.add(new MenuItem(new MenuItemData("Blue Mountain Coffee", "Blue Mountain Coffee", false, 45.5f)));
        //午餐菜单
        MenuComponent lunchMenu = new Menu(new MenuData("lunchMenu","lunch Menu"));
        lunchMenu.add(new MenuItem(new MenuItemData("Beef Rice", "Beef Rice", false, 11.0f)));
        lunchMenu.add(new MenuItem(new MenuItemData("beef noodle", "beef noodle", false, 12.5f)));
        lunchMenu.add(new MenuItem(new MenuItemData("Potato rice", "Potato rice", true, 9.0f)));
        lunchMenu.add(coffeeSubMenu);

        MenuComponent allMenu = new Menu(new MenuData("allMenu","all Menu"));
        allMenu.add(breakfastMenu);
        allMenu.add(lunchMenu);

        Waitress waitress = new Waitress(allMenu);

        waitress.printIterator();
    }
}
