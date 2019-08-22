package behavioral.template;

import behavioral.template.demo.Coffee;
import behavioral.template.demo.CoffeineBeverage;
import behavioral.template.demo.MilkTea;
import behavioral.template.demo.Tea;

/**
 * @author : zhenyun.su
 * @comment : 适配器模式 测试
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        // 类适配器
        startTest();
        demoTest();
    }

    public static void startTest(){
    }

    public static void demoTest(){
        CoffeineBeverage coffee = new Coffee();
        coffee.prepareRecipe();

        coffee = new Tea();
        coffee.prepareRecipe();

        coffee = new MilkTea();
        coffee.prepareRecipe();
    }
}
