package concurrent.priorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class PrioritizedTask implements Runnable, Comparable<PrioritizedTask> {
    private static int counter = 0;
    private final int id = counter++;
    protected static List<PrioritizedTask> list = new ArrayList<PrioritizedTask>();
    private final int priority;

    public PrioritizedTask(int priority) {
        this.priority = priority;
        this.list.add(this);
    }

    public void run() {
        System.out.println(this);
    }

    @Override
    public int compareTo(PrioritizedTask o) {
        PrioritizedTask that = (PrioritizedTask) o;
        return priority < that.priority ? 1 : (priority > that.priority ? -1 : 0);
    }

    @Override
    public String toString() {
        return "task "+id+" delta "+priority;
    }
    public static class EndSentinel extends PrioritizedTask {
        private ExecutorService executorService;
        public EndSentinel(int priority, ExecutorService executorService) {
            super(priority);
            this.executorService = executorService;
        }
        @Override
        public void run() {
            super.run();
            for (PrioritizedTask task: list){
                System.out.println("list info: "+task);
            }
            System.out.println("EndSentinel calling shutdownNow()");
            executorService.shutdownNow();
        }
    }
}
