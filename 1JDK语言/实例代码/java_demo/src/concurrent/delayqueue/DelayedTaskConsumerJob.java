package concurrent.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class DelayedTaskConsumerJob implements  Runnable{
    private DelayQueue<DelayedTask> delayQueues;

    public DelayedTaskConsumerJob(DelayQueue<DelayedTask> delayQueues) {
        this.delayQueues = delayQueues;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                delayQueues.take().run();
            }
        }catch(Exception e){
            System.out.println("DelayedTaskConsumerJob interrupted");
        }
        System.out.println("DelayedTaskConsumerJob finish");
    }
}
