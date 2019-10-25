package concurrent.productorconsumer;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal == null){
                        wait();
                    }
                }
                System.out.println("WaitPerson got "+restaurant.meal.toString());

                synchronized (restaurant.chef){
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        }catch(InterruptedException e){
            System.out.println("WaitPerson InterruptedException:"+e.getMessage());
        }
    }
}
