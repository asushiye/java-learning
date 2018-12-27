/**
 * @author : zhenyun.su
 * @since : 2018/12/27
 */

public class CounterThread extends Thread {

    protected Counter counter = null;

    public CounterThread(String name, Counter counter) {
        super(name);
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            counter.add1(i);
        }
        System.out.println(this.getName()+" counter= "+counter.getCount()+" ;");
    }
}