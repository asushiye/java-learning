package structure.bridge.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class HandBag extends Bag {
    @Override
    public void printName() {
        System.out.println(this.color.getColor()+" HandBag");
    }
}
