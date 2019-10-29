package concurrent.latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-29
 */

public class TaskWaitJob implements  Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private CountDownLatch latch;
    private Random random = new Random(47);
    public TaskWaitJob(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try{
            latch.await();
            System.out.println(this+ "");
        }catch(InterruptedException e){
            System.out.println(this+" interrupted");
        }
    }
    @Override
    public String toString() {
        return "TaskWaitJob "+id;
    }
}
