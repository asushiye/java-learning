package creational.method;

import creational.entity.AbstractProductB;
import creational.entity.ProductB1;
import creational.entity.ProductB2;

/**
 * @author : zhenyun.su
 * @comment : 1.工厂方法模式
 * @since : 2019/8/7
 */

public class ProductB2Factory extends AbstractProductBFactory {
    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
