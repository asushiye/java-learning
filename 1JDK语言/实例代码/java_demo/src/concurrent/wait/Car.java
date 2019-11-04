package concurrent.wait;

/**
 * @author : zhenyun.su
 * @comment : polish 抛光，wax 打蜡
 * @since : 2019-10-25
 */

public class Car {
    private String step = "wax"; //当前执行 polish 抛光，wax 打蜡

    public synchronized void waxed(){
        this.step= "polish";
        notifyAll();

    }
    public synchronized void polished(){
        this.step= "wax";
        notifyAll();
    }

    public synchronized  void waitForWaxing() throws InterruptedException {
        while("wax".equals(step)){
            wait();
        }
    }
    public synchronized  void waitForPolishing() throws InterruptedException {
        while("polish".equals(step)){
            wait();
        }
    }
}
