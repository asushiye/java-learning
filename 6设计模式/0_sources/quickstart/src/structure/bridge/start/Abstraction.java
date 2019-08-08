package structure.bridge.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public abstract class Abstraction {
    protected Implementor impl;

    protected Abstraction(Implementor impl) {
        this.impl = impl;
    }

    public abstract void Operation();
}
