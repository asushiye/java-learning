package creational.start.entity;

/**
 * @author : zhenyun.su
 * @comment : 可以定义华为电视
 * @since : 2019/8/7
 */

public class ProductA2 extends AbstractProductA {
    @Override
    public void service() {
        System.out.println(this.getClass().toString());
    }

    public static void main(String[] args) {
        new ProductA2().service();
    }
}
