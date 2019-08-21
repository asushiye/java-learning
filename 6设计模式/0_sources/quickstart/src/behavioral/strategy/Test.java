package behavioral.strategy;

import behavioral.strategy.start.*;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/16
 */

public class Test {
    public static void main(String[] args) {
        Dance dance = new DancePop();
        GoldenDuck goldenDuck = new GoldenDuck(dance);
        goldenDuck.dance();
        Fly fly = new FlyObject();
        WhiteDuck whiteDuck = new WhiteDuck(fly);
        whiteDuck.fly();

        goldenDuck.setDance(new DanceLatin());
        goldenDuck.dance();
    }
}
