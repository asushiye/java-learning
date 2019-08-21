package behavioral.strategy.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/16
 */

public class YellowDuck extends Duck {
    private Fly fly;
    private Dance dance;

    public YellowDuck(Fly fly, Dance dance) {
        this.fly = fly;
        this.dance = dance;
    }

    public void dance() {
        this.dance.dance();
    }

    public void fly() {
        this.fly.fly();
    }
}
