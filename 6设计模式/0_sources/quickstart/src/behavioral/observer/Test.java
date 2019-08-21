package behavioral.observer;

import behavioral.observer.demo.AObserver;
import behavioral.observer.demo.BObserver;
import behavioral.observer.demo.CObserver;
import behavioral.observer.start.BinaryObserver;
import behavioral.observer.start.HexObserver;
import behavioral.observer.start.OctalObserver;
import behavioral.observer.start.Subject;

import java.util.Observer;

/**
 * @author : zhenyun.su
 * @comment : 适配器模式 测试
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        // 类适配器
//        startTest();
        demoTest();
    }

    public static void startTest(){
        Subject subject = new Subject();

        new BinaryObserver(subject);
        new OctalObserver(subject);
        new HexObserver(subject);

        subject.setState(8);
        subject.setState(10);
    }

    public static void demoTest(){
        behavioral.observer.demo.Subject subject = new behavioral.observer.demo.Subject();
        new AObserver(subject);
        new BObserver(subject);
        Observer cobserver= new CObserver(subject);
        subject.setName("zhenyun.su");
        subject.deleteObserver(cobserver);
        subject.setName("asushiye");
    }
}
