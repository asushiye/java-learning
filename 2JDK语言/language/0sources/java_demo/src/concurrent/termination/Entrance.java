package concurrent.termination;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-24
 */

public class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private static volatile boolean cancel = false;
    private int currentValue=0;
    private final int id;

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }
    public synchronized int getCurrentValue(){
        return currentValue;
    }
    public static void isCancel(){
        cancel = true;
    }

    @Override
    public void run() {
        while(!cancel){
            synchronized (this){
                ++currentValue;
            }
            System.out.println("entrance "+id+"; value "+count.increment());
            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(InterruptedException e){
                System.out.println("sleep interrupted, "+e);
            }
        }
        System.out.println("Stopping "+this);
    }

    public static int getTotalValue(){
        return count.value();
    }
    public static int sumEntrance(){
        int sum= 0;
        for (Entrance entrance: entrances){
            sum = sum + entrance.getCurrentValue();
        }
        return sum;
    }

    @Override
    public String toString() {
        return "Entrance{" +
                "currentValue=" + currentValue +
                ", id=" + id +
                '}';
    }
}
