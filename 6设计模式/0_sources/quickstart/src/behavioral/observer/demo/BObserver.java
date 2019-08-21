package behavioral.observer.demo;

import java.util.Observable;
import java.util.Observer;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/15
 */

public class BObserver implements Observer {
    private Subject subject;

    public BObserver(Subject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Subject){
            Subject subject = (Subject)o;
            System.out.println("BObserver :"+subject.getName());
        }
    }
}
