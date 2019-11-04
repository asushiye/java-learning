package concurrent.vip;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-31
 */

public class WheelRobot extends AbstractRobot {
    public WheelRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    void perform() {
        assembler.getCar().addWheels(toString());
    }
}
