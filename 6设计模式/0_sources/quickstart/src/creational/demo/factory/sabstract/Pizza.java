package creational.demo.factory.sabstract;

import creational.demo.factory.sabstract.bom.Cheese;
import creational.demo.factory.sabstract.bom.Dough;
import creational.demo.factory.sabstract.bom.Sauce;

import java.util.ArrayList;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/19
 */

public abstract class Pizza {

    protected String name;
    protected Dough dough;
    protected Sauce sauce;
    protected ArrayList toppings = new ArrayList();
    protected Cheese cheese;

    public abstract void prepare();

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

    public void setName(String name) {
        this.name = name;
    }
}
