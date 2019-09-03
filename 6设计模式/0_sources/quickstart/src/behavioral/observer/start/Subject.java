package behavioral.observer.start;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment : 主题
 * @since : 2019/8/14
 */

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    private int state;
    public void attach(Observer observer){
        this.observers.add(observer);
    }

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        this.state = state;
        this.notifyAllObserver();
    }

    public void notifyAllObserver(){
        for (Observer observer: observers){
            observer.update();
        }
    }
}
