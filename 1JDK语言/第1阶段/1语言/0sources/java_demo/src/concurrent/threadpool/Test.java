package concurrent.threadpool;

import concurrent.wait.WaxJob;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class Test {
    public static void main(String[] args) {
        scheduledThreadPoolExecutor(args);
    }
    public static void scheduledThreadPoolExecutor(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
        System.out.println(System.currentTimeMillis()+" Scheduled Start");
        executor.schedule(new WaterJob(), 5000, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(new LightJob(), 1000, 3000,TimeUnit.MILLISECONDS);
//        executor.scheduleWithFixedDelay(new LightJob(), 1000, 3000,TimeUnit.MILLISECONDS);
        try{
            TimeUnit.SECONDS.sleep(10);
            executor.shutdown();
        }catch(Exception e){

        }
    }

}
