package concurrent.termination;

import java.util.Random;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-24
 */

public class Count {
    private int count = 0;
    private Random random = new Random(47);
    public synchronized int value(){
        return count;
    }
    public synchronized int increment(){
        int temp = count;
        if (random.nextBoolean()){
            Thread.yield();
        }
        return (count = ++temp);
    }
}
