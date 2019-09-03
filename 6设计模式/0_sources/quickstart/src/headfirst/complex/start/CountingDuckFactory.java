package headfirst.complex.start;

/**
 * @author : zhenyun.su
 * @comment : 可计数的鸭子工厂
 * @since : 2019/9/3
 */

public class CountingDuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createGreenDuck() {
        return new QuackCounter(new GreenDuck());
    }
    @Override
    public Quackable createRedDuck() {
        return new QuackCounter(new RedDuck());
    }
    @Override
    public Quackable createCallDuck() {
        return new QuackCounter(new CallDuck());
    }
    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
