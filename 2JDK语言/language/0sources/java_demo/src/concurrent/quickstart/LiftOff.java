package concurrent.quickstart;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-22
 */

public class LiftOff implements Runnable {
    protected Integer downCount=8;
    private static Integer counter=0;
    protected final Integer id= counter++;

    @Override
    public void run() {
        while (downCount >= 0){
            System.out.print(id+"("+downCount+") ");
            downCount--;
            Thread.yield();
        }
    }
}
