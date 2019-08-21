package behavioral.chain.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/13
 */

public abstract class AbstractLogger {
    public static Integer INFO=1;
    public static Integer DEBUG=2;
    public static Integer ERROR=3;
    protected Integer level;

    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(Integer level, String msg){
        if (level >= this.level){
            this.write(msg);
        }
        if (nextLogger != null){
            nextLogger.logMessage(level, msg);
        }
    }

    abstract protected void write(String msg);
}
