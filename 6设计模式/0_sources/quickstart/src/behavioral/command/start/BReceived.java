package behavioral.command.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/15
 */

public class BReceived implements Received {
    @Override
    public void doing(){
        System.out.println("BReceived doing");
    }
}
