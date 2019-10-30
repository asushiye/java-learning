package concurrent.priorityqueue;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class PrioritizedConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> queues;
    public PrioritizedConsumer(PriorityBlockingQueue<Runnable> queues) {
        this.queues = queues;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                queues.take().run();
            }
        }catch(InterruptedException e){
            System.out.println("PrioritizedConsumer interrupted");
        }
        System.out.println("PrioritizedConsumer finish");
    }
}
