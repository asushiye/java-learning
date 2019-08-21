package creational.demo.factory.method;

import java.util.ArrayList;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public abstract class Pizza {

    protected String name;
    protected String dough;
    protected String sauce;
    protected ArrayList toppings = new ArrayList();

    public void prepare(){
        System.out.println("Preparing "+name);
        System.out.println("Tossing dough "+dough);
        System.out.println("Adding sauce "+sauce);
        System.out.println("Adding toppings:");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println("    "+toppings.get(i));
        }
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

    public String getName() {
        return name;
    }
}
