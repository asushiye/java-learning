package creational.demo.factory.sabstract.bom;

/**
 * @author : zhenyun.su
 * @comment :garlic
 * @since : 2019/8/19
 */

public class ChicagoPizzaBomFactory implements PizzaBomFactory {
    @Override
    public Cheese createCheese() {
        System.out.println("use garlic cheese");
        return new GarlicCheese();
    }

    @Override
    public Dough createDough() {
        System.out.println("use garlic dough");
        return new GarlicDough();
    }

    @Override
    public Sauce createSauce() {
        System.out.println("use garlic sauce");
        return new GarlicSauce();
    }
}
