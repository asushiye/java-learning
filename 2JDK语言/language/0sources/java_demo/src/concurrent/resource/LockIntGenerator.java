package concurrent.resource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public class LockIntGenerator extends AbstractGenerator<Integer> {
    private Integer count = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public Integer next() {
        lock.lock();
        try{
            ++count;
//        Thread.yield(); 这个在锁下，已经没用
            ++count;
            return count;
        }finally{
            lock.unlock();
        }
    }
}
