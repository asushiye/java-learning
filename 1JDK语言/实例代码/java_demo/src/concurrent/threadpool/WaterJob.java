package concurrent.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-30
 */

public class WaterJob implements Runnable {
    @Override
    public void run() {
        try{
            System.out.print(System.currentTimeMillis()+" Water Job ");
        }catch(Exception e){
            System.out.println("Interrupted");
        }
        System.out.println("finish");
    }
}
