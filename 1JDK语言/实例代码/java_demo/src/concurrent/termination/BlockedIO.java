package concurrent.termination;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-24
 */

public class BlockedIO implements Runnable {
    private InputStream in;
    public BlockedIO(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("BlockedIo waiting for read()");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()){
                System.out.println("BlockedIo isInterrupted");
            }else{
                System.out.println("BlockedIo exception "+ e.getMessage());
            }
        }
        System.out.println("BlockedIo exit Run()");
    }
}
