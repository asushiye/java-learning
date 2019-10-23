package concurrent.quickstart;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-22
 */

public class SleepingTask extends LiftOff {
    @Override
    public void run() {
        try{
            while (downCount >= 0){
                System.out.print(id+"("+downCount+") ");
                downCount--;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
