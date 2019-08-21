package behavioral.command.demo.command;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/20
 */

public interface Command {
    public void execute();
    public void undo();
}
