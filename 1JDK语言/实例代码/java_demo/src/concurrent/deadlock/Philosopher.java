package concurrent.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment : 哲学家
 * @since : 2019-10-28
 */

public class Philosopher implements Runnable {
    private Chopstick right;
    private Chopstick left;
    private final int id;
    private final int ponder;
    private Random random = new Random(47);

    public Philosopher(Chopstick right, Chopstick left, int id, int ponder) {
        this.right = right;
        this.left = left;
        this.id = id;
        this.ponder = ponder;
    }

    private void pause() throws InterruptedException {
        if (ponder == 0) {
            return;
        } else {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(ponder * 250));
        }
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println(this+" thinking");
                pause();
                System.out.println(this+" take right");
                right.take();
                System.out.println(this+" take left");
                left.take();
                pause();
                System.out.println(this+" drop right");
                right.drop();
                System.out.println(this+" drop left");
                left.drop();
            }
        }catch(InterruptedException e){
            System.out.println(this+" interrupted");
        }
    }

    @Override
    public String toString() {
        return "Philosopher "+id;
    }
}
