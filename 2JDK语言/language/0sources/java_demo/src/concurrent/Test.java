package concurrent;

import java.util.concurrent.*;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-22
 */

public class Test {
    public static void main(String[] args) {
//        runable();
//        thread();
//        multiThread();
//        cachedThreadPool();
//        fixedThreadPool();
//        singleThreadPool();
//        taskWithResult();
//        sleepingTask();
//        daemonTask();
        daemonTaskFromThreadFactory();
    }

    public static void runable() {
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }

    public static void thread() {
        Thread thread = new Thread(new LiftOff());
        thread.start();
    }

    public static void multiThread() {
        System.out.println("multiThread begin");
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new LiftOff());
            thread.start();
        }
        System.out.println("multiThread end");
    }

    public static void cachedThreadPool() {
        System.out.println("cachedThreadPool");
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 3 ; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }
    public static void fixedThreadPool() {
        System.out.println("fixedThreadPool");
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3 ; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }

    public static void singleThreadPool() {
        System.out.println("singleThreadPool");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3 ; i++) {
            executor.execute(new LiftOff());
        }
        executor.shutdown();
    }

    public static void threadPoolExecutor(){
//        System.out.println("threadPoolExecutor");
//        ThreadFactory namedThreadFactory = new DefaultThreadFactory().setNameFormat("demo-pool-%d").build();
//
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//

    }


    public static void taskWithResult(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> stringFuture = executorService.submit(new TaskWithResult(1));
        try{
            if (stringFuture.isDone()){
                System.out.println(stringFuture.get());
            }else{
                System.out.println("TaskWithResult is not done");
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void sleepingTask() {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }

    public static void daemonTask() {
        try{
            for (int i = 0; i < 3; i++) {
                Thread daemon = new Thread(new SleepingTask());
                daemon.setDaemon(true);
                daemon.start();
            }
            TimeUnit.MILLISECONDS.sleep(200);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public static void daemonTaskFromThreadFactory() {
        try{
            ExecutorService executorService = Executors.newCachedThreadPool(new DaemonThreadFactory());
            for (int i = 0; i < 3; i++) {
                executorService.execute(new SleepingTask());
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}