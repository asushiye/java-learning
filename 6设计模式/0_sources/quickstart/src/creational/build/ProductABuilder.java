package creational.build;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/7
 */

public class ProductABuilder extends ProductBuilder {

    @Override
    public void build(){
        this.product.setName("productA");
        this.product.setTitle("build productA");
    };
}
