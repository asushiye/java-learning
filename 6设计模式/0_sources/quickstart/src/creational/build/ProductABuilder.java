package creational.build;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/7
 */

public class ProductABuilder extends ProductBuilder {

    @Override
    public void buildPart1(){
        this.product.setName("productA");
    };
    @Override
    public void buildPart2(){
        this.product.setTitle("build productA");
    };

}
