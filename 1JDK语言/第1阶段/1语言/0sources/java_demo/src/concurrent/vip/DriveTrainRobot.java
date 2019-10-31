package concurrent.vip;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-31
 */

public class DriveTrainRobot extends AbstractRobot {
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }

    @Override
    void perform() {
        assembler.getCar().addDriveTrain(toString());
    }
}
