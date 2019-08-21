package behavioral.chain.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/13
 */

public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(Integer level) {
        this.level = level;
    }

    @Override
    protected void write(String msg) {
        System.out.println("Console::Logger: "+msg);
    }
}
