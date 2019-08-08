package creational.build;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/7
 */

public abstract class ProductBuilder {
    protected Product product;
    public ProductBuilder() {
        this.product = new Product();
    }
    public abstract void buildPart1();
    public abstract void buildPart2();
    public Product getInstance(){
        return this.product;
    }
}
