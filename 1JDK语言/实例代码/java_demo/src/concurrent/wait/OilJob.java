package concurrent.wait;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class OilJob implements Runnable {
    private Car car;
    public OilJob(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println("Car is oiling");
                car.waitForWaxing();
                TimeUnit.MICROSECONDS.sleep(200);
                car.polished();
            }
        }catch(Exception e){
            System.out.println("OilJob, exception:"+e.getMessage());
        }
        System.out.println("OilJob, Car finished polishing");
    }
}
