package concurrent.termination;

import jdk.internal.util.xml.impl.Input;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-24
 */

public class Test {
    public static void main(String[] args) throws Exception {
//        termination();
//        interrupt1();
//        interrupt2();
//        interrupt3();
//        blockedTermination();
//        IOBlockedInterrupt();
        interruptJob();
    }

    public static void termination()  {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Entrance(i));
        }
        try{
            TimeUnit.SECONDS.sleep(2);
            Entrance.isCancel();
            executorService.shutdownNow();
            if (!executorService.awaitTermination(250, TimeUnit.MILLISECONDS)){
                System.out.println("all entrance is not termination");
            }else{
                System.out.println("all entrance is termination");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Total Value: "+Entrance.getTotalValue());
        System.out.println("Sum of Entrance: "+Entrance.sumEntrance());
    }
    public static void interrupt3(){
        System.out.println("NoBlocked is Interrupting");
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future= executorService.submit(new NoBlocked());
        try{
            TimeUnit.MICROSECONDS.sleep(10);
        }catch(Exception e){
            System.out.println("NoBlocked exception"+e.getMessage());
        }
        future.cancel(true);

        System.out.println("NoBlocked is Interrupted");
        System.exit(0);
    }

    public static void interrupt2(){
        System.out.println("NoBlocked is Interrupting");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new NoBlocked());
        try{
            TimeUnit.MICROSECONDS.sleep(10);
        }catch(Exception e){
            System.out.println("NoBlocked exception"+e.getMessage());
        }
        executorService.shutdownNow();
        System.out.println("NoBlocked is Interrupted");
        System.exit(0);
    }

    public static void interrupt1(){
        System.out.println("NoBlocked is Interrupting");
        Thread thread = new Thread(new NoBlocked());
        thread.start();
        try{
            TimeUnit.MICROSECONDS.sleep(10);
        }catch(Exception e){
            System.out.println("NoBlocked exception"+e.getMessage());
        }
        thread.interrupt();

        System.out.println("NoBlocked is Interrupted");
        System.exit(0);
    }


    public static void testBlocked(Runnable a){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future= executorService.submit(a);
        System.out.println(a.getClass().getSimpleName()+" is Interrupting");
        future.cancel(true);
        System.out.println(a.getClass().getSimpleName()+" is Interrupted");
    }

    public static void blockedTermination() {
        testBlocked(new BlockedSleep());
        testBlocked(new BlockedIO(System.in));
        testBlocked(new BlockedSynchronized());
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch(Exception e){

        }
        System.out.println("Aborting with system.exit(0)");
        System.exit(0);
    }

    public static void IOBlockedInterrupt() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try{
            new ServerSocket(8080);
            InputStream socketInput = new Socket("localhost", 8080).getInputStream();
            executorService.execute(new BlockedIO(socketInput));
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println("Shutting down all thread ");
            executorService.shutdownNow();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Closing "+ socketInput.getClass().getSimpleName());
            socketInput.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void interruptJob() {
        Thread thread = new Thread(new InterruptJob());
        try{
            thread.start();
            TimeUnit.MILLISECONDS.sleep(1500);
            thread.interrupt();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
