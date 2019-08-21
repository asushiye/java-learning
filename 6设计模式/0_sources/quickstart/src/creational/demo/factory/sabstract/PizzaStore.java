package creational.demo.factory.sabstract;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public abstract class PizzaStore {

    public Pizza orderPizza(String name){
        System.out.println("create "+name+ " pizza");
        Pizza pizza = this.createPizza(name);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    public abstract Pizza createPizza(String name);
}
