package behavioral.template.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/22
 */

public class Coffee extends CoffeineBeverage {

    @Override
    public void brew(){
        System.out.println("brew coffee");
    }
    @Override
    public void addCondiments(){
        System.out.println("add sugar and milk");
    }
}
