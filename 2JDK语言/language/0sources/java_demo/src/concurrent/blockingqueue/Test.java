package concurrent.blockingqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class Test {
    public static void main(String[] args) {
        toastBlockingQueue();
    }

    public static void toastBlockingQueue() {
        ToastQueue dryQueue = new ToastQueue(),
                butterQueue = new ToastQueue(),
                jammerQueue = new ToastQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new DryJob(dryQueue));
        executorService.execute(new ButterJob(dryQueue,butterQueue));
        executorService.execute(new JammerJob(butterQueue,jammerQueue));
        executorService.execute(new EatJob(jammerQueue));
        try{
            TimeUnit.SECONDS.sleep(1);
            executorService.shutdownNow();
        }catch(Exception e){
        }
    }
}
