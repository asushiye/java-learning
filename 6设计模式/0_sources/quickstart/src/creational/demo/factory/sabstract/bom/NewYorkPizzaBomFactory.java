package creational.demo.factory.sabstract.bom;

/**
 * @author : zhenyun.su
 * @comment :garlic
 * @since : 2019/8/19
 */

public class NewYorkPizzaBomFactory implements PizzaBomFactory {
    @Override
    public Cheese createCheese() {
        System.out.println("use Reggiano cheese");
        return new ReggianoCheese();
    }

    @Override
    public Dough createDough() {
        System.out.println("use Thin Crust dough");
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        System.out.println("use Marinara sauce");
        return new MarinaraSauce();
    }
}
