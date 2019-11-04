package concurrent.termination;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-24
 */

public class BlockedSleep implements Runnable {
    public static int count= 0;
    @Override
    public void run() {
        try{
            ++count;
        }catch(Exception e){
            System.out.println("BlockedSleep InterruptedException ");
        }
        System.out.println("BlockedSleep exit Run()");
    }
}
