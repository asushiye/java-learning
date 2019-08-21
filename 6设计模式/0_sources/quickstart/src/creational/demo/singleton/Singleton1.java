package creational.demo.singleton;

/**
 * @author : zhenyun.su
 * @comment :  同步的单实例模式 - 缺点每次获取实例，需要进行检查，性能慢100倍
 * @since : 2019/8/19
 */

public class Singleton1 {
    public static Singleton1 instance;

    private Singleton1() {
    }

    public static synchronized Singleton1 getInstance(){
        if (instance == null){
            instance= new Singleton1();
        }
        return instance;
    }
}
