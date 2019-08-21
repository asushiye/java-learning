package creational.start.entity;

/**
 * @author : zhenyun.su
 * @comment : 可以定义华为手机
 * @since : 2019/8/7
 */

public class ProductB2 extends AbstractProductB {
    @Override
    public void service() {
        System.out.println(this.getClass().toString());
    }

    public static void main(String[] args) {
        new ProductB2().service();
    }
}
