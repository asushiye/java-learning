package concurrent.blockingqueue;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class JammerJob implements Runnable{
    private ToastQueue butterQueue, jammerQueue;

    public JammerJob(ToastQueue butterQueue, ToastQueue jammerQueue) {
        this.butterQueue = butterQueue;
        this.jammerQueue = jammerQueue;
    }

    @Override
    public void run() {
        try{
            System.out.println("JammerJob start");
            while(!Thread.interrupted()){
                //若dryQueue队列无元素，则本线程阻塞
                Toast t= butterQueue.take();
                t.jammed();
                System.out.println("JammerJob"+t.toString());
                jammerQueue.put(t);
            }
            System.out.println("JammerJob success");
        }catch(InterruptedException ie){
            System.out.println("JammerJob interrupted");
        }catch (Exception e){
            System.out.println("JammerJob exception "+e.getMessage());
        }
        System.out.println("JammerJob  finish");
    }
}
