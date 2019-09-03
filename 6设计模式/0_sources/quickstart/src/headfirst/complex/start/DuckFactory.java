package headfirst.complex.start;

/**
 * @author : zhenyun.su
 * @comment : 鸭子工厂
 * @since : 2019/9/3
 */

public class DuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createGreenDuck() {
        return new GreenDuck();
    }
    @Override
    public Quackable createRedDuck() {
        return new RedDuck();
    }
    @Override
    public Quackable createCallDuck() {
        return new CallDuck();
    }
    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
