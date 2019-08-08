package structure.bridge;

import structure.bridge.demo.Bag;
import structure.bridge.demo.Color;
import structure.bridge.demo.HandBag;
import structure.bridge.demo.RedColor;
import structure.bridge.start.Abstraction;
import structure.bridge.start.ConcreteImplementorA;
import structure.bridge.start.Implementor;
import structure.bridge.start.RefinedAbstractionA;

/**
 * @author : zhenyun.su
 * @comment : 桥接模式，按需要提供类，具有很扩展性，按需要实现Implementor具体类和具体扩展抽象类
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        //快速入门
        startTest();
        //实战演练
        GetRedHandBagTest();
        //根据配置，完成实战演练
        dynamicBagTest("structure.bridge.demo.RedColor", "structure.bridge.demo.HandBag");
        dynamicBagTest("structure.bridge.demo.RedColor", "structure.bridge.demo.WalletBag");

        dynamicBagTest("structure.bridge.demo.BlueColor", "structure.bridge.demo.HandBag");
        dynamicBagTest("structure.bridge.demo.BlueColor", "structure.bridge.demo.WalletBag");

    }

    public static void startTest() {
        Implementor implementor = new ConcreteImplementorA();
        Abstraction abstraction = new RefinedAbstractionA(implementor);
        abstraction.Operation();
    }

    public static void GetRedHandBagTest() {
        Color redColor = new RedColor();
        Bag handBag= new HandBag();
        handBag.setColor(redColor);
        handBag.printName();
    }


    public static void dynamicBagTest(String colorClass, String BagClass) {
        try{
            Class<?> colorClazz = Class.forName(colorClass);
            Color color = (Color)colorClazz.newInstance();

            Class<?> BagClazz = Class.forName(BagClass);
            Bag bag = (Bag)BagClazz.newInstance();

            bag.setColor(color);
            bag.printName();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
