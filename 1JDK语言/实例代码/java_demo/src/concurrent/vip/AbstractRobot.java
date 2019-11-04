package concurrent.vip;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-31
 */

public abstract class AbstractRobot implements Runnable {
    private RobotPool pool;
    private boolean power= false;
    protected Assembler assembler;

    public AbstractRobot(RobotPool pool) {
        this.pool = pool;
    }

    abstract void perform();

    public synchronized void powerOn(Assembler assembler){
        power = true;
        this.assembler = assembler;
        notifyAll();
    }

    public synchronized void powerOff() throws InterruptedException {
        power = false;
        assembler = null;
        pool.add(this);
        while (!power){
            wait();
        }
    }

    @Override
    public void run() {
        try{
            powerOff();
            while(!Thread.interrupted()){
                perform();
                assembler.getBarrier().await();
                powerOff();
            }
        }catch(InterruptedException e){
            System.out.println(this+" interrupted");
        }catch (BrokenBarrierException bbe){
            System.out.println(this+ " BrokenBarrierException "+bbe.getMessage());
        }
        System.out.println(this+" finish");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
