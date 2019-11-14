import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-14
 */

public class Counter {
    private static int idindex = 0;
    private final int id = idindex++;
    private int count=0;

    public int inc() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(300);
        return ++count;
    }

    public int getCount() {
        return count;
    }

    public void print() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Counter{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }
}
