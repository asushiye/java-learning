package concurrent.resource;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public class Test {
    public static void main(String[] args) {
//        eventInt();
//        eventLockInt();
//        atomTest();
//        atomClassTest();
        threadLocal();
    }

    public static void eventInt() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntGenerator intGenerator = new IntGenerator();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new EventJob(intGenerator));
        }
        executorService.shutdown();
    }

    public static void eventLockInt() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        AbstractGenerator<Integer> intGenerator = new LockIntGenerator();
        for (int i = 0; i < 10; i++) {
            executorService.execute(new EventJob(intGenerator));
        }
        executorService.shutdown();
    }

    public static void atomTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomJob atomJob = new AtomJob();
        executorService.execute(atomJob);
        while(true){
            int index= atomJob.getValue();
            if (index % 2 != 0){
                System.out.println(index);
                System.exit(0);
            }else{
                System.out.println(index);
            }

        }
    }

    public static void atomClassTest() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 5000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomClassJob atomJob = new AtomClassJob();
        executorService.execute(atomJob);
        while(true){
            int index= atomJob.getValue();
            if (index % 2 != 0){    //由于保证原子性操作，这段代码基本不会再执行
                System.out.println(index);
                System.exit(0);
            }else{
                System.out.println(index);
            }
        }
    }

    public static void threadLocal() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 1000);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new ThreadLocalJob(i));
        }
        executorService.shutdown();
    }
}