package concurrent.vip;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-31
 */

public class EngineRobot extends AbstractRobot {
    public EngineRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    void perform() {
        assembler.getCar().addEngine(toString());
    }
}
