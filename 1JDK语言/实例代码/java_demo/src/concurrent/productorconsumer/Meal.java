package concurrent.productorconsumer;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-25
 */

public class Meal {
    private final int id;
    public Meal(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Meal "+ id;
    }
}
