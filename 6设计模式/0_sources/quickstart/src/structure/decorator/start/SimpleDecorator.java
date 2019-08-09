package structure.decorator.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class SimpleDecorator extends ConcreteComponent {
    private ConcreteComponent concreteComponent;

    public SimpleDecorator(ConcreteComponent concreteComponent) {
        this.concreteComponent = concreteComponent;
        System.out.println("实例装饰类");
    }

    @Override
    public void operation() {
        super.operation();
        this.addFunction();
    }

    public void addFunction(){
        System.out.println("调用装饰类的增强功能");
    }
}
