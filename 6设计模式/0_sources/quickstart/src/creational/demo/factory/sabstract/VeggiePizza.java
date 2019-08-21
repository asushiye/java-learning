package creational.demo.factory.sabstract;

import creational.demo.factory.sabstract.bom.PizzaBomFactory;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public class VeggiePizza extends Pizza {
    private PizzaBomFactory pizzaBomFactory;

    public VeggiePizza(PizzaBomFactory pizzaBomFactory) {
        this.pizzaBomFactory = pizzaBomFactory;
    }

    @Override
    public void prepare() {
        System.out.println("Preparing "+this.name);
        this.dough = pizzaBomFactory.createDough();
        this.sauce = pizzaBomFactory.createSauce();
        this.cheese = pizzaBomFactory.createCheese();
    }
}
