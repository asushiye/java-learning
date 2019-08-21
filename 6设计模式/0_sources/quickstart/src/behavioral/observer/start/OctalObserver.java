package behavioral.observer.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/14
 */

public class OctalObserver extends Observer{
    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: "+Integer.toOctalString(this.subject.getState()));
    }
}
