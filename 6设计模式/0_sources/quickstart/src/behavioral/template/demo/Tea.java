package behavioral.template.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/22
 */


public class Tea extends CoffeineBeverage {

    @Override
    public void brew(){
        System.out.println("steep tea");
    }
    @Override
    public void addCondiments(){
        System.out.println("add lemon");
    }
}
