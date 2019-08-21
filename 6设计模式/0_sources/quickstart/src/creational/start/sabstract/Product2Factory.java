package creational.start.sabstract;

import creational.start.entity.*;

/**
 * @author : zhenyun.su
 * @comment : 1.抽象工厂模式
 * @since : 2019/8/7
 */

public class Product2Factory extends AbstractProductFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
