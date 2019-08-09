package structure.decorator;


import structure.decorator.demo.BlueTheme;
import structure.decorator.demo.DefaultTheme;
import structure.decorator.demo.RedTheme;
import structure.decorator.demo.Theme;
import structure.decorator.start.*;

/**
 * @author : zhenyun.su
 * @comment : 装饰模式,对已有类实现功能增强
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        //快速入门
        startTest();
        demoTest();
    }

    public static void startTest() {
        Component component = new ConcreteComponent();
        component.operation();
        Decorator decorator = new ConcreteDecorator1(component);
        decorator.operation();

        ConcreteComponent concreteComponent = new ConcreteComponent();
        concreteComponent.operation();
        SimpleDecorator simpleDecorator = new SimpleDecorator(concreteComponent);
        simpleDecorator.operation();
    }
    public static void demoTest() {
        Theme theme = new DefaultTheme();
        theme.display();

        BlueTheme blueTheme= new BlueTheme(theme);
        blueTheme.display();

        RedTheme redTheme= new RedTheme(theme);
        redTheme.display();
    }
}
