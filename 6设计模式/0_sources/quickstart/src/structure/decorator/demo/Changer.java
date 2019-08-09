package structure.decorator.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class Changer implements Theme {
    protected Theme theme;

    public Changer(Theme theme) {
        this.theme = theme;
    }

    @Override
    public void display() {
        this.theme.display();
    }
}
