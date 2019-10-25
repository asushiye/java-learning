package concurrent.wait;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class Test {
    public static void main(String[] args) {
//        carWaxAndPolish();
        carWaxAndPolishAndOil();
    }

    public static void carWaxAndPolish() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Car car = new Car();
        executorService.execute(new PolishJob(car));
        executorService.execute(new WaxJob(car));
        try{
            TimeUnit.MILLISECONDS.sleep(10);
        }catch(Exception e){
            //
        }
        executorService.shutdownNow();
    }

    public static void carWaxAndPolishAndOil() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Car car = new Car();
        executorService.execute(new PolishJob(car));
        executorService.execute(new WaxJob(car));
        Car car1 = new Car();
        executorService.execute(new OilJob(car1));
        try{
            TimeUnit.MILLISECONDS.sleep(10);
        }catch(Exception e){
            //
        }
        executorService.shutdownNow();
    }
}
