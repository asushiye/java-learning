package creational.demo.factory.simple;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class PizzaStore {

    public Pizza orderPizza(String name){
        System.out.println("create "+name+ " pizza");
        Pizza pizza = SimplePizzaFactory.createPizza(name);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
