package concurrent.io;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-28
 */

public class WriteJob implements Runnable {
    private PipedWriter pipedWriter = new PipedWriter();
    private Random random = new Random(47);

    public PipedWriter getPipedWriter() {
        return pipedWriter;
    }

    @Override
    public void run() {
        try {
            for (char i = 'A'; i <= 'z'; i++) {
                pipedWriter.write(i);
                TimeUnit.MILLISECONDS.sleep(random.nextInt(50));
            }
        } catch (IOException ioe) {
            System.out.println("WriteJob IOException: " + ioe.getMessage());
        } catch (InterruptedException ie) {
            System.out.println("WriteJob interrupted");
        } catch (Exception e) {
            System.out.println("WriteJob exception:" + e.getMessage());
        }
        System.out.println("WriteJob finish");
    }
}
