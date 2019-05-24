/**
 * @author : zhenyun.su
 * @since : 2018/12/25
 */

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("thread subclass running"+this.getName());
    }
}
