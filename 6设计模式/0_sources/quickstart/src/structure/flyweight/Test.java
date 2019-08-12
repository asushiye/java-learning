package structure.flyweight;


import structure.flyweight.start.Circle;
import structure.flyweight.start.CircleFactory;

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
        String colors[] = { "Red", "Green", "Blue", "White", "Black" };
        CircleFactory circleFactory =  new CircleFactory();

        for (int i = 1; i <= 4; i++) {
            Circle circle = circleFactory.getCircle(String.valueOf(i));
            circle.setColor(colors[i]);
            circle.setX(i*2);
            circle.setY(i*3);
            circle.setRadius(i);
            circle.draw();
        }

        System.out.println("---------------------------");
        for (int i = 4; i >=1; i--) {
            Circle circle = circleFactory.getCircle(String.valueOf(i));
            circle.setColor(colors[i]);
            circle.setX(i*2);
            circle.setY(i*3);
            circle.setRadius(i);
            circle.draw();
        }
    }
}
