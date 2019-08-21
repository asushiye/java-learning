package creational.start.method;

import creational.start.entity.AbstractProductA;
import creational.start.entity.ProductA2;

/**
 * @author : zhenyun.su
 * @comment : 1.工厂方法模式
 * @since : 2019/8/7
 */


public class ProductA2Factory extends AbstractProductAFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }
}
