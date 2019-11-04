package concurrent.delayqueue;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class DelayedTask implements Delayed {
    private static int counter = 0;
    private final int id = counter++;
    protected static List<DelayedTask> list = new ArrayList<DelayedTask>();
    private final int delta;
    private final long tigger;

    public DelayedTask(int delta) {
        this.delta = delta;
        this.tigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        this.list.add(this);

    }

    public void run() {
        System.out.println(this);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        return tigger < that.tigger ? -1 : (tigger > that.tigger ? 1 : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((tigger - System.nanoTime()), TimeUnit.NANOSECONDS);
    }

    @Override
    public String toString() {
        return "task "+id+" delta "+delta;
    }
    public static class EndSentinel extends DelayedTask{
        private ExecutorService executorService;
        public EndSentinel(int delta, ExecutorService executorService) {
            super(delta);
            this.executorService = executorService;
        }
        @Override
        public void run() {
            super.run();
            for (DelayedTask task: list){
                System.out.println("list info: "+task);
            }
            System.out.println("EndSentinel calling shutdownNow()");
            executorService.shutdownNow();
        }
    }
}
