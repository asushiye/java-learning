import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class MyConsumer implements Runnable{

    protected BlockingQueue queue = null;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6; i++) {
                System.out.println("consumer: "+queue.take());
            }
        }catch (InterruptedException  e){
            System.out.println(e.getStackTrace());
        }
    }

    public MyConsumer(BlockingQueue queue) {
        this.queue = queue;
    }
}
