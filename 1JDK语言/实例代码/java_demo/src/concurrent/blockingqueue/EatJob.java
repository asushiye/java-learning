package concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class EatJob implements Runnable{
    private ToastQueue jammerQueue;
    private int count = 0;
    private Random random = new Random(47);

    public EatJob(ToastQueue jammerQueue) {
        this.jammerQueue = jammerQueue;
    }

    @Override
    public void run() {
        try{
            System.out.println("EatJob start");
            while(!Thread.interrupted()){
                Toast t= jammerQueue.take();
                if (t.getId() !=count++ ||t.getStatus() !=Toast.Status.JAMMED){
                    System.out.println("EatJob toast status error:" +t);
                    System.exit(1);
                }else{
                    System.out.println("EatJob "+t+" , toast is finally");
                }
            }
            System.out.println("EatJob success");
        }catch(InterruptedException ie){
            System.out.println("EatJob interrupted");
        }catch (Exception e){
            System.out.println("EatJob exception "+e.getMessage());
        }
        System.out.println("EatJob  finish");
    }
}
