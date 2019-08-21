package creational.demo.factory.method;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class NewYorkPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String name) {
        if (name.equals("cheese")){
            return new NyCheesePizza();
        }else if (name.equals("clam")){
            return new NyClamPizza();
        }else if (name.equals("veggie")){
            return new NyVeggiePizza();
        }
        return null;
    }
}
