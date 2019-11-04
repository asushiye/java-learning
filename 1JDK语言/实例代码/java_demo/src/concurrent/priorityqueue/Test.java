package concurrent.priorityqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> tasks = new PriorityBlockingQueue<>();
        executorService.execute(new PrioritizedProducer(tasks,executorService));
        executorService.execute(new PrioritizedConsumer(tasks));
    }
}
