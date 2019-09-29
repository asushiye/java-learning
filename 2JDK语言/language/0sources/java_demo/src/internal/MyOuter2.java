package internal;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-09-29
 */

public class MyOuter2 {
    private static int x;

    static class MyInner {
        void go() {
            x = 50;
            System.out.println(x);
        }
    }
}
