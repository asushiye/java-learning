package creational.demo.factory.simple;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class Test {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new PizzaStore();
        Pizza pizza = pizzaStore.orderPizza("cheese");

        pizza = pizzaStore.orderPizza("clam");

        pizza = pizzaStore.orderPizza("veggie");

    }
}
