package jvm.arthas;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-14
 */

public class CounterJob implements Runnable {
    private Counter counter;

    public CounterJob(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println("CounterJob, running");
        try{
            while(!Thread.interrupted()){
                synchronized (counter){
                    counter.inc();
                    TimeUnit.MILLISECONDS.sleep(200);
                    counter.print();
                }
            }
        }catch(Exception e){
            System.out.println("CounterJob, exception:"+e.getMessage());
        }
        System.out.println("CounterJob, finish");
    }
}
