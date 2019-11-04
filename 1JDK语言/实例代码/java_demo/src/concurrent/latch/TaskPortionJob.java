package concurrent.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-29
 */

public class TaskPortionJob implements  Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private CountDownLatch latch;
    private Random random = new Random(47);
    public TaskPortionJob(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
            System.out.println("; CountDownLatch "+latch.getCount());
        }catch(InterruptedException e){
            System.out.println(this+" interrupted");
        }
    }

    private void doWork() throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep (random.nextInt(500));
        System.out.print(this + " completed");
    }

    @Override
    public String toString() {
        return "TaskPortionJob "+id;
    }
}
