package creational.singleton;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/10
 */

public class SynchronizedObject {
    private volatile static SynchronizedObject instance;

    private SynchronizedObject() {
    }

    public static SynchronizedObject getInstance() {
        if (instance == null) {
            synchronized (SynchronizedObject.class) {
                if (instance == null) {
                    return new SynchronizedObject();
                }
            }
        }
        return instance;
    }

    public void show() {
        System.out.println("SynchronizedObject is good");
    }
}
