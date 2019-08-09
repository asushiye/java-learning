package structure.facade.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/9
 */

public class Facade {
    private SubSystem1 subSystem1;
    private SubSystem2 subSystem2;
    private SubSystem3 subSystem3;

    public Facade() {
        this.subSystem1 = new SubSystem1();
        this.subSystem2 = new SubSystem2();
        this.subSystem3 = new SubSystem3();
    }
    public void Server(){
        this.subSystem1.server1();
        this.subSystem2.server2();
        this.subSystem3.server3();
    }
}
