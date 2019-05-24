import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyDelayQueue {

    public static void main(String[] args){
        BlockingQueue queue =  new DelayQueue();

        Delayed delayed = new Delayed() {
            @Override
            public long getDelay(TimeUnit unit) {
                return unit.toSeconds();
            }

            @Override
            public int compareTo(Delayed o) {
                return 0;
            }
        };



    }
}
