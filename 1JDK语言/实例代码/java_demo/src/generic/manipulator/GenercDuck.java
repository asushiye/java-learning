package generic.manipulator;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-21
 */

public class GenercDuck {
    public static <T extends Animal> void genercDuck(T a){
        a.eat();
    }

    public static void main(String[] args) {
        GenercDuck.genercDuck(new Duck());
    }
}
