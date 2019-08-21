package creational.demo.factory.method;

/**
 * @author : zhenyun.su
 * @comment : 奶酪披萨
 * @since : 2019/8/19
 */

public class NyCheesePizza extends Pizza {
    public NyCheesePizza() {
        this.name="New York style sauce and cheese pizza";
        //薄面团
        this.dough="thin crust dough";
        //意大利西红柿酱
        this.sauce="Marinara Sauce";
        toppings.add("grated reggiano cheese");
    }
}
