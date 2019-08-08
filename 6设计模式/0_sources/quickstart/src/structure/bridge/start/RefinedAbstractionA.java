package structure.bridge.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class RefinedAbstractionA extends Abstraction {

    public RefinedAbstractionA(Implementor impl) {
        super(impl);
    }

    @Override
    public void Operation() {
        System.out.println("具体抽象化代码被执行");
        this.impl.OperationImp();
    }
}
