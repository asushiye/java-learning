package behavioral.command.start;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/15
 */

public class ACommand implements Command {
    private AReceived aReceived;

    public ACommand() {
        this.aReceived = new AReceived();
    }
    @Override
    public void execute() {
        this.aReceived.doing();
    }
}
