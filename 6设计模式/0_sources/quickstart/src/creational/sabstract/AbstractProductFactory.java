package creational.sabstract;

import creational.entity.AbstractProductA;
import creational.entity.AbstractProductB;

/**
 * @author : zhenyun.su
 * @comment : 1.抽象工厂模式
 * @since : 2019/8/7
 */

public abstract class AbstractProductFactory {
    public abstract AbstractProductA createProductA();
    public abstract AbstractProductB createProductB();
}
