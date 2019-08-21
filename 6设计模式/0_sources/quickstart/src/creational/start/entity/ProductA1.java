package creational.start.entity;

/**
 * @author : zhenyun.su
 * @comment : 可以定义小米电视
 * @since : 2019/8/7
 */

public class ProductA1 extends AbstractProductA {
    @Override
    public void service() {
        System.out.println(this.getClass().toString());
    }

    public static void main(String[] args) {
        new ProductA1().service();
    }
}
