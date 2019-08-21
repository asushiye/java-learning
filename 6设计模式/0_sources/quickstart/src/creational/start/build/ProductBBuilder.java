package creational.start.build;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/7
 */

public class ProductBBuilder extends ProductBuilder {

    @Override
    public void build(){
        this.product.setName("productB");
        this.product.setTitle("build productB");
    };
}
