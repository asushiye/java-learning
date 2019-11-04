package concurrent.termination;

/**
 * @author : zhenyun.su
 * @comment : ืสิด
 * @since : 2019-10-25
 */

public class InterruptResource {
    private int id;
    public InterruptResource(int id) {
        this.id = id;
        System.out.println("need resource "+ id);
    }
    public void cleanResource(){
        System.out.println("clean resource "+ id);
    }
}
