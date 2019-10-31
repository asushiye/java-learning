package concurrent.vip;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-31
 */

public class Assembler implements Runnable{
    private CarQueue chassisQueue, finishingQueue;
    private RobotPool pool;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);

    public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool pool) {
        this.chassisQueue = chassisQueue;
        this.finishingQueue = finishingQueue;
        this.pool = pool;
    }

    public synchronized Car getCar() {
        return car;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                car = chassisQueue.take();
                pool.hive(EngineRobot.class, this);
                pool.hive(DriveTrainRobot.class, this);
                pool.hive(WheelRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
                System.out.println("---------------------------");
            }
        }catch(InterruptedException ie){
            System.out.println("Assembler Interrupted");
        }catch (BrokenBarrierException bbe){
            System.out.println("Assembler BrokenBarrierException");
        }
        System.out.println("Assembler finish");
    }
}
