package internal;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-09-29
 */

public class Test {
    public static void main(String[] args) {
        MyOuter myOuter =  new MyOuter();
        MyOuter.MyInner myInnerClass = myOuter.new MyInner();
        myInnerClass.go();

        MyOuter2.MyInner myInner = new MyOuter2.MyInner();
        myInner.go();
    }
}
