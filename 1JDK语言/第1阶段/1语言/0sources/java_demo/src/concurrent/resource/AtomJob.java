package concurrent.resource;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public class AtomJob implements Runnable {
    private volatile int i=0;
    public synchronized int getValue(){
        return i;
    }
    public synchronized void increment(){
        i++;
        i++;
    }
    @Override
    public void run() {
        while(true){
            increment();
        }
    }
}
