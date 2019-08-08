package structure.bridge.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public abstract class Bag {
    protected Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract void printName();
}
