package concurrent.io;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class Test {
    public static void main(String[] args) throws  Exception{
        WriteJob writeJob = new WriteJob();
        ReadJob readJob = new ReadJob(writeJob);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(writeJob);
        executorService.execute(readJob);
    }
}
