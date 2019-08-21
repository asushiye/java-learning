package creational.demo.factory.simple;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public abstract class Pizza {

    public void prepare(){
        System.out.println("pizza prepare");
    }

    public void bake(){
        System.out.println("bake pizza");
    }
    public void cut(){
        System.out.println("cut pizza");
    }

    public void box(){
        System.out.println("box pizza");
    }

}
