package behavioral.chain.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/13
 */

public class DataBaseLogger extends AbstractLogger {

    public DataBaseLogger(Integer level) {
        this.level = level;
    }

    @Override
    protected void write(String msg) {
        System.out.println("Data Base::Logger: "+msg);
    }
}
