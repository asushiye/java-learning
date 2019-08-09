package structure.decorator.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        this.component.operation();
    }
}
