package structure.decorator.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class ConcreteDecorator1 extends Decorator {
    public ConcreteDecorator1(Component component) {
        super(component);
        System.out.println("实例装饰子类");
    }
    @Override
    public void operation() {
        super.operation();
        this.addFunction();
    }
    public void addFunction(){
        System.out.println("调用装饰子类1的增强功能");
    }
}
