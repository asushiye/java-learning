package headfirst.complex.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/3
 */

public abstract class AbstractDuckFactory {
    public abstract Quackable createGreenDuck();
    public abstract Quackable createRedDuck();
    public abstract Quackable createCallDuck();
    public abstract Quackable createRubberDuck();
}
