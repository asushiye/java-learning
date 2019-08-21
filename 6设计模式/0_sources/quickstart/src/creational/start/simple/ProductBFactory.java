package creational.start.simple;

import creational.start.entity.*;

/**
 * @author : zhenyun.su
 * @comment : 1.简单工厂模式
 * @since : 2019/8/7
 */

public class ProductBFactory {
    public AbstractProductB createProductA(String name){
        if (name.equals("ProductB1")){
            return new ProductB1();
        }
        if (name.equals("ProductB2")){
            return new ProductB2();
        }
        return null;
    }
}
