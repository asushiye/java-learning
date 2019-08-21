package creational.demo.factory.method;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String name) {
        if (name.equals("cheese")){
            return new CcgCheesePizza();
        }else if (name.equals("clam")){
            return new CcgClamPizza();
        }else if (name.equals("veggie")){
            return new CcgVeggiePizza();
        }
        return null;
    }
}
