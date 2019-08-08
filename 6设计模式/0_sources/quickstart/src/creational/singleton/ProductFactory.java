package creational.singleton;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/7
 */

public class ProductFactory {

    private Product product;

    public Product getInstane(){
        if (this.product == null){
            this.product = new Product();
        }
        return this.product;
    }
}
