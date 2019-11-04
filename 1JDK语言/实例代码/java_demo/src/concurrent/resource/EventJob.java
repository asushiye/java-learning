package concurrent.resource;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-23
 */

public class EventJob implements Runnable {
    private AbstractGenerator<Integer> generator;

    public EventJob(AbstractGenerator<Integer> generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        while (!generator.isCancel()) {
            Integer index = generator.next();
            if (index % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + " not event; value:"+index);
                generator.cancel();
            }
        }
    }
}
