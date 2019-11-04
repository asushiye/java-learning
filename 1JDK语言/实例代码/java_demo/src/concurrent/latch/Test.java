package concurrent.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-29
 */

public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int count = 5;
        CountDownLatch latch= new CountDownLatch(count);
        for (int i = 0; i < 20; i++) {
            executorService.execute(new TaskWaitJob(latch));
        }
        for (int i = 0; i < count; i++) {
            executorService.execute(new TaskPortionJob(latch));
        }
        System.out.println("launched all tasks");
        executorService.shutdown();
    }
}
