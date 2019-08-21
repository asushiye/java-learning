package creational.start.sabstract;

import creational.start.entity.*;

/**
 * @author : zhenyun.su
 * @comment : 1.抽象工厂模式
 * @since : 2019/8/7
 */

public class Product1Factory extends AbstractProductFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
