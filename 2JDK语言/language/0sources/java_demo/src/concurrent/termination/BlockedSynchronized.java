package concurrent.termination;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-24
 */

public class BlockedSynchronized  implements  Runnable{

    public synchronized void f(){
        while (true) Thread.yield();
    }

    public BlockedSynchronized() {
        new Thread(){
            public void Run(){
                f(); //lock acquired by this thread
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("BlockedSynchronized try to call f()");
        f();
        System.out.println("BlockedSynchronized exit Run()");
    }
}
