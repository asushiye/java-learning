package creational.demo.factory.sabstract;


import creational.demo.factory.sabstract.bom.NewYorkPizzaBomFactory;
import creational.demo.factory.sabstract.bom.PizzaBomFactory;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class NewYorkPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String name) {
        Pizza pizza = null;
        PizzaBomFactory pizzaBomFactory =new NewYorkPizzaBomFactory();
        if (name.equals("cheese")){
            pizza= new CheesePizza(pizzaBomFactory);
            pizza.setName("New York Style Cheese Pizza");
        }else if (name.equals("clam")){
            pizza= new ClamPizza(pizzaBomFactory);
            pizza.setName("New York Style Clam Pizza");
        }else if (name.equals("veggie")){
            pizza= new VeggiePizza(pizzaBomFactory);
            pizza.setName("New York Style Veggie Pizza");
        }
        return pizza;
    }
}
