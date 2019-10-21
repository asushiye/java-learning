package generic.manipulator;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-21
 */

public class Duck implements Animal {
    @Override
    public void eat() {
        System.out.println("duck eat");
    }

    @Override
    public void swiming() {
        System.out.println("duck swiming");
    }
}
