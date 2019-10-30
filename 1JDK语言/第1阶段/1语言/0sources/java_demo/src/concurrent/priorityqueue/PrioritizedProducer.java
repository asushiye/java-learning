package concurrent.priorityqueue;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class PrioritizedProducer implements Runnable {
    private Random random = new Random(47);
    private Queue<Runnable> queues;
    private ExecutorService executorService;

    public PrioritizedProducer(Queue<Runnable> queues, ExecutorService executorService) {
        this.queues = queues;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            queues.add(new PrioritizedTask(random.nextInt(3)));
        }
        try{
            for (int i = 0; i < 2; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queues.add(new PrioritizedTask(3));
            }
            for (int i = 0; i < 2; i++) {
                queues.add(new PrioritizedTask(i));
            }

            queues.add(new PrioritizedTask.EndSentinel(-1, executorService));
        }catch(InterruptedException e){
            System.out.println("PrioritizedProducer interrupted");
        }
        System.out.println("PrioritizedProducer finish");
    }
}
