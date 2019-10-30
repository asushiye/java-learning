package concurrent.delayqueue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class Test {
    public static void main(String[] args) {
        final int maxDelta = 5000;
        Random random = new Random(47);
        ExecutorService executorService = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queues = new DelayQueue<>();
        for (int i = 0; i < 20; i++) {
            queues.put(new DelayedTask(random.nextInt(maxDelta)));
        }
        queues.add(new DelayedTask.EndSentinel(maxDelta, executorService));
        executorService.execute(new DelayedTaskConsumerJob(queues));
    }
}
