package creational.method;

import creational.entity.AbstractProductA;
import creational.entity.ProductA1;
import creational.entity.ProductA2;

/**
 * @author : zhenyun.su
 * @comment : 1.工厂方法模式
 * @since : 2019/8/7
 */

public class ProductA1Factory extends AbstractProductAFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }
}
