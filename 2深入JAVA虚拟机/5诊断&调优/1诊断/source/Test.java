import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-13
 */

public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run(){
                int i=0;
                try{
                    while(!Thread.interrupted()){
                        i++;
                        String s1 = new String("success");
                        System.out.println(i+" "+Instant.now()+ " thread "+s1);
                        TimeUnit.SECONDS.sleep(1);
                    }
                }catch(Exception e){
                    System.out.println("interrupted");
                }
                System.out.println("finish");
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(thread);
         try{
            TimeUnit.SECONDS.sleep(500);
            executorService.shutdownNow();
        }catch(Exception e){

        }
    }
}
