package creational.start.sabstract;

import creational.start.entity.AbstractProductA;
import creational.start.entity.AbstractProductB;

/**
 * @author : zhenyun.su
 * @comment : 1.抽象工厂模式
 * @since : 2019/8/7
 */

public abstract class AbstractProductFactory {
    public abstract AbstractProductA createProductA();
    public abstract AbstractProductB createProductB();
}
