package creational.demo.factory.method;

/**
 * @author : zhenyun.su
 * @comment : 奶酪披萨
 * @since : 2019/8/19
 */

public class CcgCheesePizza extends Pizza {
    public CcgCheesePizza() {
        this.name="Chicago style Deep fish cheese pizza";
        //薄面团
        this.dough="Extra thick crust dough";
        //意大利西红柿酱
        this.sauce="tomato Sauce";
        toppings.add("mozzarella cheese");
    }

    @Override
    public void cut() {
        System.out.println("cutting the pizza into square slices");
    }
}
