package headfirst.complex.start;

/**
 * @author : zhenyun.su
 * @comment : 叫声计数器
 * @since : 2019/9/3
 */

public class QuackCounter implements Quackable {
    private Quackable quck;
    private static int count=0;
    public QuackCounter(Quackable quck) {
        this.quck = quck;
    }
    @Override
    public void quack() {
        quck.quack();
        count++;
    }
    public static int getCount(){
        return count;
    }
}
