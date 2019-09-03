package headfirst.complex.start;

/**
 * @author : zhenyun.su
 * @comment : 鹅适配器
 * @since : 2019/9/3
 */

public class GooseAdapter implements Quackable {
    private Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        this.goose.honk();
    }
}
