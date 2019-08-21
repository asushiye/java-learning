package creational.start.build;

/**
 * @author : zhenyun.su
 * @comment : 指挥者
 * @since : 2019/8/7
 */

public class Director {
    private ProductBuilder productBuilder;

    public void setProductBuilder(ProductBuilder productBuilder) {
        this.productBuilder = productBuilder;
    }
    public Product construct(){
        productBuilder.build();
        return productBuilder.getInstance();
    }
}
