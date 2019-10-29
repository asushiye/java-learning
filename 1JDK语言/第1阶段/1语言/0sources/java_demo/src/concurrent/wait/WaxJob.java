package concurrent.wait;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class WaxJob implements Runnable {
    private Car car;
    public WaxJob(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                TimeUnit.MICROSECONDS.sleep(200);
                System.out.println("Car is waxing");
                car.waxed();
                car.waitForPolishing();
            }
        }catch(InterruptedException e){
            System.out.println("WaxJob, exception:"+e.getMessage());
        }
        System.out.println("WaxJob, Car finished waxing");
    }
}
