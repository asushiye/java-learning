package creational.singleton;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/10
 */

public class SingletonObject {
    private static SingletonObject instance = new SingletonObject();
    private SingletonObject() {}
    public static SingletonObject getInstance(){
        return instance;
    }
    public void show(){
        System.out.println("SingletonObject is good");
    }
}
