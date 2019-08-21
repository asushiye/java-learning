package creational.demo.factory.sabstract;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class Test {
    public static void main(String[] args) {
        System.out.println("=======i need New York pizza=======");
        PizzaStore pizzaStore = new NewYorkPizzaStore();
        Pizza pizza = pizzaStore.orderPizza("cheese");
//        pizza = pizzaStore.orderPizza("clam");
//        pizza = pizzaStore.orderPizza("veggie");
//
        System.out.println("=======i need Chicago pizza=======");
        pizzaStore = new ChicagoPizzaStore();
        pizza = pizzaStore.orderPizza("cheese");
//        pizza = pizzaStore.orderPizza("clam");
//        pizza = pizzaStore.orderPizza("veggie");
    }
}
