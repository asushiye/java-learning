package concurrent.resource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public class AtomClassJob implements Runnable {
    private AtomicInteger i= new AtomicInteger(0);
    public int getValue(){
        return i.get();
    }
    public void increment(){
        i.addAndGet(2);
    }
    @Override
    public void run() {
        while(true){
            increment();
        }
    }
}
