package concurrent.resource;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public class ThreadLocalJob implements Runnable {
    private final int id;

    public ThreadLocalJob(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "id"+ id+" value: "+ThreadLocalVariableHolder.get();
    }
}
