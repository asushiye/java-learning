package concurrent.vip;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment : 给Car队列插入创建Car
 * @since : 2019-10-31
 */

public class CarQueueJob implements  Runnable {
    private CarQueue carQueue;
    private int counter= 0;

    public CarQueueJob(CarQueue carQueue) {
        this.carQueue = carQueue;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Car car = new Car(counter++);
                System.out.println("CarQueueJob "+car);
                carQueue.put(car);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }catch(InterruptedException e){
            System.out.println("CarQueueJob interrupted");
        }
        System.out.println("CarQueueJob finish");
    }
}
