package structure.bridge.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class ConcreteImplementorA implements Implementor {
    @Override
    public void OperationImp() {
        System.out.println("具体实现化代码被执行");
    }
}
