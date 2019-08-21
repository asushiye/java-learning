package creational.demo.factory.simple;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class SimplePizzaFactory {

    public static Pizza createPizza(String name){
        if (name.equals("cheese")){
            return new CheesePizza();
        }else if (name.equals("clam")){
            return new ClamPizza();
        }else if (name.equals("veggie")){
            return new VeggiePizza();
        }
        return null;
    }
}
