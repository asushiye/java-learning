package concurrent.blockingqueue;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class DryJob implements Runnable{
    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);

    public DryJob(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try{
            System.out.println("DryJob start");
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                Toast t= new Toast(count++);
                System.out.println("DryJob"+ t.toString());
                toastQueue.put(t);
            }
            System.out.println("DryJob success");
        }catch(InterruptedException ie){
            System.out.println("DryJob interrupted");
        }catch (Exception e){
            System.out.println("DryJob exception "+e.getMessage());
        }
        System.out.println("DryJob  finish");
    }
}
