package concurrent.deadlock;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class Chopstick {
    private boolean taked = false;
    public synchronized void take() throws InterruptedException {
        while (taked)
            wait();
        taked = true;
    }

    public synchronized void drop(){
        taked = false;
        notifyAll();
    }
}
