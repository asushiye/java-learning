package behavioral.strategy.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/16
 */

public class WhiteDuck extends Duck {
    private Fly fly;

    public WhiteDuck(Fly fly) {
        this.fly = fly;
    }

    public void fly() {
        this.fly.fly();
    }
}
