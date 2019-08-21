package creational.start.method;

import creational.start.entity.AbstractProductB;
import creational.start.entity.ProductB1;

/**
 * @author : zhenyun.su
 * @comment : 1.工厂方法模式
 * @since : 2019/8/7
 */

public class ProductB1Factory extends AbstractProductBFactory {
    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
