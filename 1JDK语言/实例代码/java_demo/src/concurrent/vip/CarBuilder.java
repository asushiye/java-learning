package concurrent.vip;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-31
 */

public class CarBuilder {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CarQueue chassisQueue = new CarQueue();
        CarQueue finishQueue = new CarQueue();
        RobotPool pool = new RobotPool();
        executorService.execute(new EngineRobot(pool));
        executorService.execute(new DriveTrainRobot(pool));
        executorService.execute(new WheelRobot(pool));
        executorService.execute(new Assembler(chassisQueue, finishQueue, pool));
        executorService.execute(new ReporterQueueJob(finishQueue));
        executorService.execute(new CarQueueJob(chassisQueue));
        TimeUnit.SECONDS.sleep(1);
        executorService.shutdownNow();
    }
}
