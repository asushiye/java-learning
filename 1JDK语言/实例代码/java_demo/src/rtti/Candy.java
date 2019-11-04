package rtti;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-04
 */

public class Candy extends SuperCandy implements Swimable, Driverable {
    private String name = "suzhenyun";
    static final int id= 0;
    static int count= 0;
    static void print(){
        System.out.println("Print Candy");
    }
    static {
        System.out.println("Loading Candy");
    }

    public Candy() {
        super();
        System.out.println("Construct Candy");
    }

    @Override
    public void drive() {
        System.out.println("Candy can drivering");
    }

    @Override
    public void swim() {
        System.out.println("Candy can swimming");
    }
}
