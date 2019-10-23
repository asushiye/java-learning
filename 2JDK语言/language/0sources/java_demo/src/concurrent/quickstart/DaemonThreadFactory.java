package concurrent.quickstart;

import java.util.concurrent.ThreadFactory;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-22
 */

public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
