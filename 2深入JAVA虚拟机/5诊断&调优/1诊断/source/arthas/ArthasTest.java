import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-14
 */

public class ArthasTest {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new CounterJob(counter));
        executorService.execute(new CounterJob(counter));
        try{
            TimeUnit.SECONDS.sleep(600);
            executorService.shutdownNow();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
