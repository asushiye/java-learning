package creational.method;

import creational.entity.AbstractProductA;
import creational.entity.ProductA1;

/**
 * @author : zhenyun.su
 * @comment : 1.工厂方法模式
 * @since : 2019/8/7
 */

public abstract class AbstractProductAFactory {
    public abstract AbstractProductA createProductA();
}
