package concurrent.productorconsumer;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class Chef implements  Runnable {
    private Restaurant restaurant;
    private volatile int count = 0;
    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal != null){
                        wait();
                    }
                }

                if (++count > 5){
                    System.out.println("Out of food, closing!");
                    restaurant.executorService.shutdownNow();
                }else{
                    System.out.print("Order up, chef has completed "+count+" meal, ");
                }

                synchronized (restaurant.waitPerson){
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch(InterruptedException e){
            System.out.println("Chef Interrupted"+e.getMessage());
        }
    }

}
