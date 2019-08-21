package creational.demo.factory.sabstract;


import creational.demo.factory.sabstract.bom.ChicagoPizzaBomFactory;
import creational.demo.factory.sabstract.bom.NewYorkPizzaBomFactory;
import creational.demo.factory.sabstract.bom.PizzaBomFactory;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String name) {
        Pizza pizza = null;
        PizzaBomFactory pizzaBomFactory =new ChicagoPizzaBomFactory();
        if (name.equals("cheese")){
            pizza= new CheesePizza(pizzaBomFactory);
            pizza.setName("Chicago Style Cheese Pizza");
        }else if (name.equals("clam")){
            pizza= new ClamPizza(pizzaBomFactory);
            pizza.setName("Chicago Style Clam Pizza");
        }else if (name.equals("veggie")){
            pizza= new VeggiePizza(pizzaBomFactory);
            pizza.setName("Chicago Style Veggie Pizza");
        }
        return pizza;
    }
}
