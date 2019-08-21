package creational.demo.factory.sabstract.bom;

/**
 * @author : zhenyun.su
 * @comment : 披萨成分工厂
 * @since : 2019/8/19
 */

public interface PizzaBomFactory {

    public Cheese createCheese();
    public Dough createDough();
    public Sauce createSauce();
}
