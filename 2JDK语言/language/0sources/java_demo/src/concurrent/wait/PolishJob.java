package concurrent.wait;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class PolishJob implements Runnable {
    private Car car;
    public PolishJob(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println("Car is polishing");
                car.waitForWaxing();
                TimeUnit.MICROSECONDS.sleep(200);
                car.polished();
            }
        }catch(Exception e){
            System.out.println("PolishJob, exception:"+e.getMessage());
        }
        System.out.println("PolishJob, Car finished polishing");
    }
}
