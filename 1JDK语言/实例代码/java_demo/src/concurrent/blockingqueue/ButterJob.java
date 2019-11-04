package concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class ButterJob implements Runnable{
    private ToastQueue dryQueue, butterQueue;

    public ButterJob(ToastQueue dryQueue, ToastQueue butterQueue) {
        this.dryQueue = dryQueue;
        this.butterQueue = butterQueue;
    }

    @Override
    public void run() {
        try{
            System.out.println("ButterJob start");
            while(!Thread.interrupted()){
                //若dryQueue队列无元素，则本线程阻塞
                Toast t= dryQueue.take();
                t.buttered();
                System.out.println("ButterJob "+t.toString());
                butterQueue.put(t);
            }
            System.out.println("ButterJob success");
        }catch(InterruptedException ie){
            System.out.println("ButterJob interrupted");
        }catch (Exception e){
            System.out.println("ButterJob exception "+e.getMessage());
        }
        System.out.println("ButterJob  finish");
    }
}
