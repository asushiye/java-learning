package concurrent.vip;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment : 给Car队列插入创建Car
 * @since : 2019-10-31
 */

public class ReporterQueueJob implements  Runnable {
    private CarQueue carQueue;
    public ReporterQueueJob(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println("ReporterQueueJob "+carQueue.take());
            }
        }catch(InterruptedException e){
            System.out.println("CarQueueJob interrupted");
        }
        System.out.println("CarQueueJob finish");
    }
}
