import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyBlockingQueue {
    public static void main(String[] args)
    {
        try {
            BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

            MyProducer producer = new MyProducer(blockingQueue);
            MyConsumer consumer =  new MyConsumer(blockingQueue);

            new Thread(producer).start();
            new Thread(consumer).start();

            Thread.sleep(4000);

        }catch (InterruptedException e){
            System.out.println(e.getStackTrace());
        }
    }
}
