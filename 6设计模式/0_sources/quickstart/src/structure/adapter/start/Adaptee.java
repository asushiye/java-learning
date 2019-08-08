package structure.adapter.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/8
 */

public class Adaptee {
    public void specificRequest(){
        System.out.println("适配者中的业务代码被调用！");
    }
}
