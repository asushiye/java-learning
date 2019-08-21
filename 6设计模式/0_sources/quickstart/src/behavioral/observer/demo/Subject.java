package behavioral.observer.demo;

import java.util.Observable;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/15
 */

public class Subject extends Observable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.statusChange();
    }
    public void statusChange(){
        super.setChanged();
        super.notifyObservers();
    }
}
