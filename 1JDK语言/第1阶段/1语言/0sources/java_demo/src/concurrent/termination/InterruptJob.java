package concurrent.termination;

import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class InterruptJob implements Runnable {
    private volatile double d = 1;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                InterruptResource resource1 = new InterruptResource(1);
                try {
                    System.out.println("thread sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    InterruptResource resource2 = new InterruptResource(2);
                    try {
                        System.out.println("thread calculating");
                        for (int i = 1; i < 25000000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("thread calculated" );
                    } finally {
                        resource2.cleanResource();
                    }
                } finally {
                    resource1.cleanResource();
                }
            }
            System.out.println("Exiting via normal interrupted");
        } catch (InterruptedException e) {
            System.out.println("Exiting via exception interrupted, error: " + e.getMessage());
        }
    }
}
