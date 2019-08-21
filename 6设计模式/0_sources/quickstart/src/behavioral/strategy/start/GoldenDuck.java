package behavioral.strategy.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/16
 */

public class GoldenDuck extends Duck {
    private Dance dance;

    public GoldenDuck(Dance dance) {
        this.dance = dance;
    }

    public void setDance(Dance dance) {
        this.dance = dance;
    }

    public void dance() {
        this.dance.dance();
    }
}
