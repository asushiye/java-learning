package concurrent.blockingqueue;

/**
 * @author : zhenyun.su
 * @comment : ÍÂË¾
 * @since : 2019-10-28
 */

public class Toast {
    public enum Status {DRY,BUTTERED, JAMMED};
    private Status status = Status.DRY;
    private final  int id;
    public Toast(int id) {
        this.id = id;
    }
    public void buttered(){
        status = Status.BUTTERED;
    }
    public void jammed(){
        status= Status.JAMMED;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Toast "+id +": "+ status;
    }
}
