/**
 * @author : zhenyun.su
 * @since : 2018/12/27
 */

public class Counter {

    private long count=0;

    public long getCount() {
        return count;
    }

    public synchronized void add(int value){
        this.count += value;
    }

    public void add1(int value){
        synchronized(this){
        this.count += value;
        }
    }

}
