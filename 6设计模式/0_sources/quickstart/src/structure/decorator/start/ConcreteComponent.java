package structure.decorator.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class ConcreteComponent implements Component {
    public ConcreteComponent() {
        System.out.println("实例构件");
    }

    @Override
    public void operation() {
        System.out.println("调用构件方法");
    }
}
