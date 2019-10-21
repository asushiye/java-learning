package generic.manipulator;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-21
 */

public class Mainpulator<T extends HasF> {
    T obj;

    public Mainpulator(T obj) {
        this.obj = obj;
    }

    public void print(){
        obj.f();
    }

    public static void main(String[] args) {
        HasF hf= new HasF();
        Mainpulator<HasF> mainpulator = new Mainpulator<>(hf);
        mainpulator.print();
    }
}
