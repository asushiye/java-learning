package concurrent.vip;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-31
 */

public class RobotPool {
    private Set<AbstractRobot> pool = new HashSet<AbstractRobot>();
    public synchronized void add(AbstractRobot robot){
        pool.add(robot);
    }

    public synchronized void hive(Class<? extends AbstractRobot> robotType, Assembler a)
            throws InterruptedException {
        for (AbstractRobot robot: pool){
            if (robot.getClass().equals(robotType)){
                pool.remove(robot);
                robot.powerOn(a);
                return ;
            }
        };
        wait();
        hive(robotType, a);
    }
}
