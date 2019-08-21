package behavioral.observer.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/14
 */

public class BinaryObserver extends Observer{
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: "+Integer.toBinaryString(this.subject.getState()));
    }
}
