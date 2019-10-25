package concurrent.productorconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class Restaurant {
    public Meal meal;
    public WaitPerson waitPerson = new WaitPerson(this);
    public Chef chef = new Chef(this);
    ExecutorService executorService = Executors.newCachedThreadPool();

    public Restaurant() {
        executorService.execute(waitPerson);
        executorService.execute(chef);
    }

    public static void main(String[] args) {
        new Restaurant();
    }


}
