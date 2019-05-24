import java.util.concurrent.BlockingQueue;

public class MyProducer implements Runnable {

    protected BlockingQueue queue = null;

    public MyProducer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6 ; i++) {
                queue.put(String.valueOf(i));
                System.out.println("product: "+i);
            }
        }catch (InterruptedException  e){
            System.out.println(e.getStackTrace());
        }
    }
}
