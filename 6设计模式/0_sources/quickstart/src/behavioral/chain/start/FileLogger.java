package behavioral.chain.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/13
 */

public class FileLogger extends AbstractLogger {

    public FileLogger(Integer level) {
        this.level = level;
    }

    @Override
    protected void write(String msg) {
        System.out.println("File::Logger: "+msg);
    }
}
