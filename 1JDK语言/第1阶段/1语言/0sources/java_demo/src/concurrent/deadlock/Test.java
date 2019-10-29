package concurrent.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class Test {
    public static void main(String[] args) throws InterruptedException{
//        deadlockDiningPhilosopher();
        fixDiningPhilosopher();
    }

    public static void deadlockDiningPhilosopher() throws InterruptedException{
        int size = 5;
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % size], i, 0));
        }
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
    public static void fixDiningPhilosopher() throws InterruptedException{
        int size = 5;
        int ponder = 0;
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
        }
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            if (i == size-1) {
                executorService.execute(new Philosopher(chopsticks[i], chopsticks[(0)], i, ponder));
            }else {
                executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) ], i, ponder));
            }
        }
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}
