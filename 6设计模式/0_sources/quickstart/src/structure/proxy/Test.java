package structure.proxy;


import structure.proxy.start.Image;
import structure.proxy.start.ProxyImage;

/**
 * @author : zhenyun.su
 * @comment : 桥接模式，按需要提供类，具有很扩展性，按需要实现Implementor具体类和具体扩展抽象类
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        //快速入门
        startTest();
    }

    public static void startTest() {
        Image image = new ProxyImage("/p.png");
        image.display();
    }
}
