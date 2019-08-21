package creational.start.simple;

import creational.start.entity.AbstractProductA;
import creational.start.entity.ProductA1;
import creational.start.entity.ProductA2;

/**
 * @author : zhenyun.su
 * @comment : 1.简单工厂模式
 * @since : 2019/8/7
 */

public class ProductAFactory {
    public AbstractProductA createProductA(String name){
        if (name.equals("ProductA1")){
            return new ProductA1();
        }
        if (name.equals("ProductA2")){
            return new ProductA2();
        }
        return null;
    }
}
